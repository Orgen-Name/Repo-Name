<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 供应商添加页面</span>
        </div>
        <div class="providerAdd">
        <%-- 	<fm:form method="post" modelAttribute="provider">
        		<div>
        			<fm:errors path="proCode"/><br/>
                    <label for="proCode">供应商编码：</label>
                    <fm:input path="proCode"/>
					<!-- 放置提示信息 -->
					
				</div>
				<div>
					<fm:errors path="proName"/><br/>
                    <label for="proName">供应商名称：</label>
                    <fm:input path="proName"/>
                </div>
                <div>
                	<fm:errors path="proContact"/><br/>
                    <label for="proContact">联系人：</label>
                    <fm:input path="proContact"/>
                </div>
        		<div>
        			<fm:errors path="proPhone"/><br/>
                    <label for="proPhone">联系电话：</label>
                    <fm:input path="proPhone"/>
                </div>
                <div>
                	<fm:errors path="proAddress"/><br/>
                   	<label for="proAddress">联系地址：</label>
                    <fm:input path="proAddress"/>	
                </div>
                <div>
                	<fm:errors path="proFax"/><br/>
                    <label for="proFax">传真：</label>
                    <fm:input path="proFax"/>
                </div>
                <div>
                	<fm:errors path="proDesc"/><br/>
                    <label for="proDesc">概述：</label>
                    <fm:input path="proDesc"/>
                </div>
                <div class="providerAddBtn">
                    <input type="submit" name="add" id="add" value="保存" >
					<input type="button" id="back" name="back" value="返回" >
                </div>
        	</fm:form> --%>
            <form id="providerForm" name="providerForm" method="post" action="${pageContext.request.contextPath }/user/providerAddSave.html" enctype="multipart/form-data">
				<input type="hidden" name="method" value="add">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="proCode">供应商编码：</label>
                    <input type="text" name="proCode" id="proCode" value=""> 
					<!-- 放置提示信息 -->
					<font color="red"></font>
                </div>
                <div>
                    <label for="proName">供应商名称：</label>
                    <input type="text" name="proName" id="proName" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="proContact">联系人：</label>
                    <input type="text" name="proContact" id="proContact" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="proPhone">联系电话：</label>
                    <input type="text" name="proPhone" id="proPhone" value=""> 
					<font color="red"></font>
                </div>
                <div>
                   <label for="proAddress">联系地址：</label>
                    <input type="text" name="proAddress" id="proAddress" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="proFax">传真：</label>
                    <input type="text" name="proFax" id="proFax" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="proDesc">概述：</label>
                    <input type="text" name="proDesc" id="proDesc" value=""> 
					<font color="red"></font>
                </div>
                 <div>
                 	<input type="hidden" id="errorinfo_wp" value="${uploadFileError}" />
                    <label for="companyLicPicPath">企业营业执照：</label>
                    	<input type="File" name="attachs" id="a_companyLicPicPath" value=""> 
					<font color="red"></font>
                </div>
                <div>
              	    <input type="hidden" id="errorinfo_wp" value="${uploadFileError}" />
                    <label for="companyLicPicPath">组织机构代码证：</label>
                    	<input type="File" name="attachs" id="a_orgCodePicPath" value=""> 
					<font color="red"></font>
                </div>
                <div class="providerAddBtn">
                    <input type="button" name="add" id="add" value="保存" >
					<input type="button" id="back" name="back" value="返回" >
                </div>
               
            </form>
        </div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/provideradd.js"></script>
