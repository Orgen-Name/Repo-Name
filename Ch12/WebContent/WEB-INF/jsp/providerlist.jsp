<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>供应商管理页面</span>
            </div>
            <div class="search">
           		<form method="post" action="${pageContext.request.contextPath }/user/provider">
					<input name="method" value="query" class="input-text" type="hidden">
					 <span>供应商编码：</span>
					 <input name="proCode" class="input-text"	type="text" value="${proCode }"/>
					 
					 <span>供应商名称：</span>
					 <input name="proName" class="input-text"   type="text" value="${proName}"/>
					 
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="查 询" type="submit" id="searchbutton">
					 <a href="${pageContext.request.contextPath}/user/provideradd.html" >添加供应商</a>
				</form>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">供应商编码</th>
                    <th width="20%">供应商名称</th>
                    <th width="10%">联系人</th>
                    <th width="10%">联系电话</th>
                    <th width="10%">传真</th>
                    <th width="10%">创建时间</th>
                    <th width="30%">操作</th>
                </tr>
                   <c:forEach var="pro" items="${proList }" varStatus="status">
					<tr>
						<td>
						<span>${pro.proCode }</span>
						</td>
						<td>
						<span>${pro.proName }</span>
						</td>
						<td>
						<span>${pro.proContact }</span>
						</td>
						<td>
						<span>${pro.proPhone}</span>
						</td>
						<td>
						<span>${pro.proFax}</span>
						</td>
						<td>
							<span>${pro.creationDate}</span>
						</td>
						<td>
						<span><a class="viewProvider" href="javascript:;"    id=${pro.id } username=${pro.proName }><img src="${pageContext.request.contextPath }/statics/images/read.png" alt="查看" title="查看"/></a></span>
						<span><a class="modifyProvider" href="javascript:;"   id=${pro.id } username=${pro.proName }><img src="${pageContext.request.contextPath }/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>
						<span><a class="deleteProvider" href="javascript:;"    id=${pro.id } username=${pro.proName }><img src="${pageContext.request.contextPath }/statics/images/schu.png" alt="删除" title="删除"/></a></span>
						</td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
			<%--这里是相当于嵌入一个页面，还把这些值传到嵌入的页面中去。 --%>
		  	<c:import url="rollpage.jsp">
	          	<c:param name="totalCount" value="${totalCount}"/>
	          	<c:param name="currentPageNo" value="${currentPageNo}"/>
	          	<c:param name="totalPageCount" value="${totalPageCount}"/>
          	</c:import>
			 
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/providerlist.js"></script>
