<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="util.ConstantUtil"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="pojo.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index Page</title>
<head>
<link rel="stylesheet" href="styles.css">
</head>
</head>
<body>
<%
String roomID = "";
if(session.getAttribute(ConstantUtil.PLAYER) != null) {
	Player player = (Player)session.getAttribute(ConstantUtil.PLAYER);
	roomID = player.getRoomId()==null?"":player.getRoomId();
}
%>
	<p align="center"><%=session.getAttribute(ConstantUtil.SYSTEM_INFO)%></p>
	<form method="post">
		<table align="left">
			<tr>
				<td colspan=2><input id="roomID" name="roomID" value="<%=roomID%>"></td>
				<td colspan=2><input id="applyRoom" name="applyRoom" type="submit"
					value="applyRoom"></td>
			</tr>
		</table>
	</form>
	<table align="center">
		<tr>
			<%
			if(session.getAttribute("serverCard")!=null) {
				Card scard = (Card)session.getAttribute("serverCard");
			%>	
				<td colspan=2><input id="serverCard" value="<%=scard.getSuit()%><%=scard.getCardNum()%>"></td>
			<%	
			}else {
			%>
			<td colspan=2><input id="serverCard" value=""></td>
			<%			
			}
			%>
			<td colspan=2><input id="potChips" value="<%=session.getAttribute("potChips")==null?"":session.getAttribute("potChips")%>"></td>
		</tr>
	</table>
	<br>
	<br> Your Cards:
	<table>
		<tr>
			<%
			if(session.getAttribute(ConstantUtil.USER_CARDS) != null) {
				List<Card> userCards = (List<Card>)session.getAttribute(ConstantUtil.USER_CARDS);
				for(Card c: userCards) {
			%>
			<td><input value="<%=c.getSuit()%><%=c.getCardNum()%>"></td>
			<%		
				}	
			}else {
			%>
			<td><input value=""></td>
			<td><input value=""></td>
			<td><input value=""></td>
			<td><input value=""></td>
			<td><input value=""></td>
			<%	
			}
			%>
		</tr>
	</table>
	<br /> Operation:
	<br />
	<table style="width: 50%;" align="left">
		<tr>
			<td colspan=2><input id="bchips" value="1"></td>
		</tr>
		<tr>
			<td><input id="raise" type="button" value="raise"></td>
			<td><input id="reduce" type="button" value="reduce"></td>
			<td>
				<form method="post">
					<input id="bet" name="bet" type="submit" value="bet">
				</form>
			</td>
		</tr>
	</table>
	
	<table style="width: 50%;" align="right">
		<tr>
			<td colspan=3><input id="userchips" value="<%=session.getAttribute("userchips")==null?"":session.getAttribute("userchips")%>"></td>
		</tr>
		<tr>
			<td>
				<form method="post">
					<input id="start" name="start" type="submit" value="start">
				</form>
			</td>
			<td>
				<form method="post">
					<input id="quit" name="quit" type="submit" value="quit">
				</form>
			</td>
		</tr>
	</table>
	
</body>
</html>