<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
 <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 供应商信息查看页面</span>
        </div>
        <div class="providerView">
            <p><strong>供应商编号：</strong><span>${pro.proCode }</span></p>
            <p><strong>供应商名称：</strong><span>${pro.proName }</span></p>
            <p><strong>联系人：</strong><span>${pro.proContact }</span></p> 
            <p><strong>联系电话：</strong><span>${pro.proPhone }</span></p>
            <p><strong>传真：</strong><span>${pro.proFax }</span></p>
            <p><strong>描述：</strong><span>${pro.proDesc }</span></p>
			<div class="providerAddBtn">
            	<input type="button" id="back" name="back" value="返回" >
            </div>
        </div>
    </div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/userview.js"></script>