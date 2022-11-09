Demo Website - Cơm nhà:
1. Cài đặt hệ CSDL SQL Server.
2. Chạy scripts quanlynhahang_SQL.sql trong thư mục 'source demo/Database' để import CSDL và dữ liệu.
3. Vào thư mục 'source demo/Web Aplication/ComNha_API' chạy file ComNha_API.sln và để mở solution Project ComNha_API.
4. Chọn vào View -> Server Explorer để mở tab Server Explorer
5. Chuột phải vào Data Connections -> Chọn Add Connection và điền Server Name của SQL Server vào sau đó chọn Database Name 'quanlynhahang' để kết nối CSDL vào Project.
6. Sau khi kết nối thành công nhấn chuột trái vào connection vừa được thêm vào và ở phía Tab Properties. Ta copy property 'Connection String'.
7. Mở file 'appsettings.json', ở property "ConnectionStrings" ta thêm một thuộc tính dạng key-value với key là tên bất kỳ, value là Connection String ta vừa copy ở bước 6.
8. Mở thư mục Models -> Mở file 'quanlynhahangContext.cs' và tìm hàm "OnConfiguring", sau đó thay key mà chúng ta điền ở bước 7 vào dòng 42.
new ConfigurationBuilder().AddJsonFile("appsettings.json").Build().GetSection("ConnectionStrings")["(Điền key mà chúng ta thêm ở bước 7 vào đây)"]
9. Chạy project để chạy server API.
10. Vào thư mục ComNha chạy file ComNha.sln và để mở solution Project ComNha và chạy ứng dụng web ASP.NET MVC.

Demo Ứng dụng Window Form - Quản lý nhà hàng:
1. Cài đặt hệ CSDL SQL Server.
2. Chạy scripts quanlynhahang_SQL.sql trong thư mục source demo/Databaseđể import CSDL và dữ liệu.
3. Vào thư mục 'source demo/Window Form/51900286-51900712-51900718-51900699' 
chạy file '51900286-51900712-51900718-51900699.sln'để mở solution Project 51900286-51900712-51900718-51900699.
4. Chọn vào View -> Server Explorer để mở tab Server Explorer
5. Chuột phải vào Data Connections -> Chọn Add Connection và điền Server Name của SQL Server 
vào sau đó chọn Database Name 'quanlynhahang' để kết nối CSDL vào Project.
6. Sau khi kết nối thành công nhấn chuột trái vào connection vừa được thêm vào và ở phía Tab Properties. 
Ta copy property 'Connection String'.
7. Mở RestaurentDAO -> mở file DataProvider -> Paste 'Connection String' ta vừa copy ở bước 6 vào biến 'SQLConnectionString'.
8. Chạy ứng dụng.

