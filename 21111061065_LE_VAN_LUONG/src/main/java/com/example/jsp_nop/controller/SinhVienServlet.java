package com.example.jsp_nop.controller;
import com.example.jsp_nop.model.SinhVien;
import com.example.jsp_nop.service.SinhVienService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/")
public class SinhVienServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SinhVienService sinhVienService;
    public void init(){
        sinhVienService = new SinhVienService();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rqParam = req.getServletPath();
        switch (rqParam){
            case "/insert":
                showFormDangKy(req,resp);
                break;
            case "/add":
                themSinhVien(req,resp);
                break;
            case "/delete":
                xoaSinhVien(req, resp);
                break;
//            case "/edit":
//                hienThiFormSua(req, resp);
            case "/edit":
                hienThiFormSua(req, resp);
                break;
            case "/search":
                timKiemSinhVien(req, resp);
                break;

            default:
                showListSinhVien(req,resp);
                break;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("id");
        String tenSv = request.getParameter("Tensv");
        String lopSv = request.getParameter("Lop");
        String queSv = request.getParameter("Quequan");

        if (idString != null && !idString.isEmpty()) {
            try {
                Long id = Long.parseLong(idString);

                SinhVien sinhVien = new SinhVien(id, tenSv, lopSv, queSv);

                // Gọi phương thức service của bạn để cập nhật sinh viên
                boolean success = sinhVienService.capNhatSinhVien(sinhVien);

                if (success) {
                    // Nếu cập nhật thành công, chuyển hướng đến trang danh sách
                    response.sendRedirect("list");
                } else {
                    response.getWriter().println("Cập nhật sinh viên không thành công!");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("ID không hợp lệ!");
            }
        } else {
            response.getWriter().println("Không có ID được cung cấp!");
        }
    }

    private void showListSinhVien(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<SinhVien> sinhVienList = sinhVienService.layDanhSachSinhVien();
        request.setAttribute("listSinhVien",sinhVienList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("form-list.jsp");
        dispatcher.forward(request,response);
    }
    private void showFormDangKy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("form-dangky.jsp");
        dispatcher.forward(request, response);
    }
    private void themSinhVien(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String tenSv = request.getParameter("Tensv");
        String lopSv = request.getParameter("Lop");
        String queSv = request.getParameter("Quequan");
        SinhVien sinhVien = new SinhVien(tenSv,lopSv,queSv);
        sinhVienService.themSinhVien(sinhVien);
        response.sendRedirect("list"); // chuyen trang
    }
    private void xoaSinhVien(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Lấy ID sinh viên từ request
        String idString = request.getParameter("id");

        if (idString != null && !idString.isEmpty()) {
            try {
                // Chuyển đổi ID từ String sang Long
                Long id = Long.parseLong(idString);

                // Gọi phương thức xóa sinh viên từ service
                boolean success = sinhVienService.xoaSinhVien(id);

                if (success) {
                    // Nếu xóa thành công, chuyển hướng đến trang danh sách sinh viên
                    response.sendRedirect("list");
                } else {
                    // Nếu xóa không thành công, có thể xử lý bằng cách hiển thị thông báo lỗi
                    response.getWriter().println("Xóa sinh viên không thành công!");
                }
            } catch (NumberFormatException e) {
                // Xử lý lỗi khi chuyển đổi ID từ String sang Long
                response.getWriter().println("ID không hợp lệ!");
            }
        } else {
            // Xử lý khi không có ID được truyền
            response.getWriter().println("Không có ID được cung cấp!");
        }
    }

    private void hienThiFormSua(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy ID sinh viên từ request
        String idString = request.getParameter("id");

        if (idString != null && !idString.isEmpty()) {
            try {
                // Chuyển đổi ID từ String sang Long
                Long id = Long.parseLong(idString);

                // Gọi phương thức lấy sinh viên theo ID từ service
                SinhVien sinhVien = sinhVienService.laySinhVienTheoId(id);

                if (sinhVien != null) {
                    // Đưa ID và sinh viên vào request để sử dụng trong biểu mẫu sửa
                    request.setAttribute("id", id);
                    request.setAttribute("sinhVien", sinhVien);

                    // Chuyển hướng đến trang biểu mẫu sửa
                    RequestDispatcher dispatcher = request.getRequestDispatcher("form-sua.jsp");
                    dispatcher.forward(request, response);
                    return;  // Đảm bảo kết thúc phương thức sau khi chuyển hướng
                }
            } catch (NumberFormatException e) {
                // Xử lý lỗi khi chuyển đổi ID từ String sang Long
                e.printStackTrace();
            }
        }

        // Xử lý khi không có ID được truyền hoặc lỗi xảy ra
        response.getWriter().println("Không tìm thấy sinh viên hoặc có lỗi xảy ra!");
    }



    private void timKiemSinhVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<SinhVien> sinhVienList = sinhVienService.timKiemSinhVien(keyword);
        request.setAttribute("listSinhVien", sinhVienList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("form-list.jsp");
        dispatcher.forward(request, response);
    }
}






