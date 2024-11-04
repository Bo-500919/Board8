<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
  <c:set  var="startnum"        value="${ searchVo.pagination.startPage}" />
  <c:set  var="endnum"          value="${ searchVo.pagination.endPage}" />
  <c:set  var="totalpagecount"  value="${ searchVo.pagination.totalPageCount}" />
  
  <div id="paging" >
    <table >
      <tr>
       <c:if test="${ startnum gt 1 }">
         <td>
           <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=1">⏮</a>
         </td>
         <td>  
           <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${startnum-1}">◀</a>
         </td>
       </c:if> 
        
        <c:forEach var="pagenum" begin="${startnum}" end="${endnum}" step="1">
        <td>
         <c:if test="${ pagenum ne 0 }">
          <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${pagenum}">
          ${ pagenum }
         </c:if> 
          </a>
        </td>
        </c:forEach>
       
       <!-- 다음 / 마지막  -->
       <c:if test="${ endnum  ne totalpagecount }"> 
        <td>
          <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${endnum+1}"">▶</a>
        </td>
        <td>
          <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${totalpagecount}"">⏭</a>
        </td>
       </c:if>  
      </tr>
    </table>  
  </div>
  
  
  
  
  