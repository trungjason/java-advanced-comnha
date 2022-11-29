Hướng dẫn chạy Project
Mở XAMPP chạy MySQL ở PORT 3306
Tạo Database tên final 'final_term_comnha' (nếu chưa có)

=> Chạy Project (Spring Boot App) trong thư mục Dev (branch Dev)
 và kiểm tra nếu các table được tự tạo là thành công

Truy cập localhost:8080 để xem trang chủ

Coding Note:
Template engine được sử dụng là: Thymeleaf

Nhiệm vụ hiện tại là sửa lại các file HTML trong thư mục resources/templates
Từ format C# sang Thymeleaf

Thư mục Shared:
	Chứa các file Fragment (Template chung)

Sử dụng các file Fragment này để Tái sử dụng Code

Các đường dẫn ảnh nếu bị lỗi phải sửa thuộc tính src sang th:src
Đường dẫn ảnh phải sửa thành @{/images/[tên ảnh]} (tương tự với đường dẫn js và css)
