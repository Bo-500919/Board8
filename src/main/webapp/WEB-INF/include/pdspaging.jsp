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
           <a href="/Pds/List?menu_id=${map.menu_id}&nowpage=1&search=${ map.search }&searchtext=${ map.searchtext }">⏮</a>
         </td>
         <td>  
           <a href="/Pds/List?menu_id=${map.menu_id}&nowpage=${startnum-1}&search=${ map.search }&searchtext=${ map.searchtext }">◀</a>
         </td>
       </c:if> 
        
        <c:forEach var="pagenum" begin="${startnum}" end="${endnum}" step="1">
        <td>
         <c:if test="${ pagenum ne 0 }">
          <a href="/Pds/List?menu_id=${map.menu_id}&nowpage=${pagenum}&search=${ map.search }&searchtext=${ map.searchtext }">
          ${ pagenum }
         </c:if> 
          </a>
        </td>
        </c:forEach>
       
       <!-- 다음 / 마지막  -->
       <c:if test="${ endnum  ne totalpagecount }"> 
        <td>
          <a href="/Pds/List?menu_id=${map.menu_id}&nowpage=${endnum+1}&search=${ map.search }&searchtext=${ map.searchtext }">▶</a>
        </td>
        <td>
          <a href="/Pds/List?menu_id=${map.menu_id}&nowpage=${totalpagecount}&search=${ map.search }&searchtext=${ map.searchtext }">⏭</a>
        </td>
       </c:if>  
      </tr>
    </table>  
  </div>
  
  
  
  
  