<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Danh sách sinh viên</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
          crossorigin="anonymous">
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>

<h1>Quản Lý Sinh Viên</h1>
<br/>
<a href="<%=request.getContextPath()%>/insert" class="btn btn-success">Thêm Sinh Viên</a>


<h2>DANH SÁCH SINH VIÊN</h2>

<!-- Form tìm kiếm -->
<form action="search" method="get">
    <input type="text" name="keyword" placeholder="Nhập từ khóa theo tên">
    <button type="submit">Tìm kiếm</button>
</form>

<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên Sinh Viên</th>
        <th>Lớp</th>
        <th>Quê Quán</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="sinhVien" items="${listSinhVien}">
        <tr>
            <td><c:out value="${sinhVien.id}" /></td>
            <td><c:out value="${sinhVien.tenSinhVien}" /></td>
            <td><c:out value="${sinhVien.lopSinhVien}" /></td>
            <td><c:out value="${sinhVien.queQuanSinhVien}" /></td>
            <td>
                <!-- Liên kết đến phương thức xóa với tham số là ID của sinh viên -->
                <a href="<%=request.getContextPath()%>/edit?id=${sinhVien.id}" class="btn btn-success">Sửa</a>
                <a href="<%=request.getContextPath()%>/delete?id=${sinhVien.id}" class="btn btn-danger">Xóa</a>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
