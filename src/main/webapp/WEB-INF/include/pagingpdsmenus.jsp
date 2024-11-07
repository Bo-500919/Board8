<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    
  <table  id="menu">
    <tr>
    <c:forEach var="menu" items="${ menuList }">
      <td>
        <a href="/Pds/List?menu_id=${menu.menu_id}&nowpage=1">
          ${ menu.menu_name } 
        </a>
      </td>
    </c:forEach>
    </tr>
  </table>  
  