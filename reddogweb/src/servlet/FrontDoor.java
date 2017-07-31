package servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.Card;
import pojo.Player;
import service.GameService;
import util.ConstantUtil;
import util.PlayerFactory;

public class FrontDoor extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6623275929959770293L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		HttpSession session = request.getSession();
		List<Card> serverPack = null;
		List<Card> userCards = null;
		/*
		 * get room ID from session, if room ID does not exist, return
		 * systemInfo to ask user: 1. If user wants to create a new room, then
		 * click applyRoom directly; 2. If user wants to join an existing room,
		 * then enter room ID and then click applyRoom; 3. room ID use UUID for
		 * now.
		 */
		if (session.getAttribute(ConstantUtil.PLAYER) == null) {
			// a new player joined, create room ID and play order number (start
			// from 1)
			Player player = PlayerFactory.getPlayer(null, null);
			session.setAttribute(ConstantUtil.PLAYER, player);
			session.setAttribute(ConstantUtil.SYSTEM_INFO, ConstantUtil.MSG_TO_NEW_PLAYER);
		}

		if (request.getParameter(ConstantUtil.APPLY_ROOM) != null) {
			Player player = PlayerFactory.getPlayer(UUID.randomUUID().toString(), Integer.valueOf(1));
			session.setAttribute(ConstantUtil.PLAYER, player);
			session.setAttribute(ConstantUtil.SYSTEM_INFO, ConstantUtil.MSG_TO_SINGLE_PLAYER);
			session.setAttribute(ConstantUtil.USER_CARDS, null);
			session.setAttribute("potChips", 2);// default 2 chips if play with
												// robot
			session.setAttribute("userchips", 9);// default 10 chips per player,
													// add 1 to pot if start
													// with robot
		} else if (request.getParameter(ConstantUtil.START) != null) {
			serverPack = GameService.getNewPackCards();
			GameService.shuffle(serverPack);
			userCards = GameService.distributeCards(serverPack);
			session.setAttribute("serverPack", serverPack);
			session.setAttribute(ConstantUtil.USER_CARDS, userCards);
			session.setAttribute(ConstantUtil.SYSTEM_INFO, "Click 'bet' to try your luck, good luck!");
			session.setAttribute("serverCard", null);
		} else if (request.getParameter("bet") != null) {
			if(session.getAttribute("serverPack") != null 
					&& session.getAttribute(ConstantUtil.USER_CARDS) != null) {
				serverPack = (List<Card>)session.getAttribute("serverPack");
				userCards = (List<Card>)session.getAttribute(ConstantUtil.USER_CARDS);
				List<Card> winCards = GameService.bet(userCards, serverPack);
				if (winCards.isEmpty()) {
					session.setAttribute(ConstantUtil.SYSTEM_INFO, "You lose, click 'start' to continue");
					Integer userchips = (Integer) session.getAttribute("userchips");
					session.setAttribute("userchips", userchips - 1);//minus the chips on bet instead
					Integer potChips = (Integer) session.getAttribute("potChips");
					session.setAttribute("potChips", potChips + 1);//plus the chips on bet instead
				} else {
					session.setAttribute(ConstantUtil.SYSTEM_INFO, "You WIN!!! click 'start' to continue");
					Integer userchips = (Integer) session.getAttribute("userchips");
					session.setAttribute("userchips", userchips + 1);//plus the chips on bet instead
					Integer potChips = (Integer) session.getAttribute("potChips");
					session.setAttribute("potChips", potChips - 1);//minus the chips on bet instead
				}
				//show server card, and no matter win or lose, remove this card
				session.setAttribute("serverCard", serverPack.get(0));
				serverPack.remove(0);
				session.setAttribute("serverPack", serverPack);
			}
		}else if (request.getParameter("quit") != null) {
			session.invalidate();
			session = request.getSession();
			session.setAttribute(ConstantUtil.SYSTEM_INFO, ConstantUtil.MSG_TO_NEW_PLAYER);
		}

		/*
		 * user will be assigned a playing order number as unique identity in
		 * this room,
		 */

		RequestDispatcher rd = context.getRequestDispatcher(ConstantUtil._INDEX);
		rd.forward(request, response);
	}

}
