<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>用户管理页面 >> 用户修改页面</span>
	</div>
	<div class="providerAdd">
		<form id="userForm" name="userForm" method="post"
			action="${pageContext.request.contextPath }/user/changepwd.html">
			<input type="hidden" name="id" value="${user.id }" />
			<div>
				<label for="oldpassword">旧密码：</label> <input type="text"
					name="oldpassword" id="oldpassword" value=""> <font
					color="red"></font>
			</div>
			<div>
				<label for="data">新密码：</label> <input type="text" 
					id="newpassword" name="newpassword" value=""
					> <font
					color="red"></font>
			</div>
			<div>
				<label for="data">确认新密码：</label> <input type="text" 
					id="rnewpassword" name="rnewpassword" value=""
					/> <font
					color="red"></font>
			</div>
			<div class="providerAddBtn">
				<input type="button" name="save" id="save" value="保存" />
			</div>
		</form>
	</div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/pwdmodify.js"></script>
