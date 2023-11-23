<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<li><a href="<%=request.getContextPath()%>/list"
       class="nav-link">DANH SACH SV</a></li>
<form action="add" method="post">

        <input type="hidden" name="Masv"  />

    <fieldset class="form-group">
        <label>Tên Sinh Viên</label> <input type="text"
                                  name="Tensv" required="required">
    </fieldset>
    <fieldset class="form-group">
        <label>Lớp</label> <input type="text"
                                  name="Lop">
    </fieldset>
    <fieldset class="form-group">
        <label>Quê Quán</label> <input type="text"
                                  name="Quequan">
    </fieldset>
    <button type="submit">Lưu</button>
    <td>
        <a href="delete?id=${sinhVien.id}">Xóa</a>
    </td>
</form>
<!-- Thêm nút xóa vào mỗi dòng của bảng danh sách sinh viên -->


</body>
</html>
