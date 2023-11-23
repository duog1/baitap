<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sửa Sinh Viên</title>
</head>
<body>
<h2>Sửa Sinh Viên</h2>
<form action="update" method="post">
    <input type="hidden" name="id" value="${id}">
    <fieldset class="form-group">
        <label>Tên Sinh Viên</label>
        <input type="text" name="Tensv" value="${sinhVien.tenSinhVien}" required="required">
    </fieldset>
    <fieldset class="form-group">
        <label>Lớp</label>
        <input type="text" name="Lop" value="${sinhVien.lopSinhVien}">
    </fieldset>
    <fieldset class="form-group">
        <label>Quê Quán</label>
        <input type="text" name="Quequan" value="${sinhVien.queQuanSinhVien}">
    </fieldset>
    <button type="submit">Cập Nhật</button>
</form>
</body>
</html>
