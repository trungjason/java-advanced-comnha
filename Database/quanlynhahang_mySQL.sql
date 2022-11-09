-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 07, 2022 at 10:02 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlynhahang`
--
CREATE DATABASE IF NOT EXISTS `quanlynhahang` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `quanlynhahang`;

-- --------------------------------------------------------

--
-- Table structure for table `banan`
--

DROP TABLE IF EXISTS `banan`;
CREATE TABLE IF NOT EXISTS `banan` (
  `MaBanAn` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TinhTrang` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `SucChua` int(11) NOT NULL,
  `GhiChu` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaBanAn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `banan`
--

INSERT INTO `banan` (`MaBanAn`, `TinhTrang`, `SucChua`, `GhiChu`) VALUES
('BA001', 'Trống', 4, ''),
('BA002', 'Trống', 4, ''),
('BA003', 'Trống', 8, ''),
('BA004', 'Trống', 8, ''),
('BA005', 'Trống', 10, ''),
('BA006', 'Trống', 10, ''),
('BA007', 'Trống', 12, ''),
('BA008', 'Trống', 14, ''),
('BA009', 'Trống', 18, ''),
('BA010', 'Trống', 20, ''),
('BA011', 'Trống', 20, ''),
('BA012', 'Trống', 20, '');

-- --------------------------------------------------------

--
-- Table structure for table `chitietgoimon`
--

DROP TABLE IF EXISTS `chitietgoimon`;
CREATE TABLE IF NOT EXISTS `chitietgoimon` (
  `MaMonAn` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaOrder` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuongMonAn` int(11) NOT NULL,
  PRIMARY KEY (`MaMonAn`,`MaOrder`),
  KEY `FK_ChiTietGoiMon_PhieuGoiMon` (`MaOrder`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `chitietgoimon`
--

INSERT INTO `chitietgoimon` (`MaMonAn`, `MaOrder`, `SoLuongMonAn`) VALUES
('MA001', 'PGM013', 1),
('MA001', 'PGM014', 1),
('MA001', 'PGM015', 1),
('MA022', 'PGM013', 1),
('MA027', 'PGM013', 1);

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieunhap`
--

DROP TABLE IF EXISTS `chitietphieunhap`;
CREATE TABLE IF NOT EXISTS `chitietphieunhap` (
  `MaNguyenVatLieu` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaPhieuNhap` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuongNhap` int(11) NOT NULL,
  PRIMARY KEY (`MaNguyenVatLieu`,`MaPhieuNhap`),
  KEY `FK_ChiTietPhieuNhap_PhieuNhap` (`MaPhieuNhap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
CREATE TABLE IF NOT EXISTS `hoadon` (
  `MaHoaDon` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `ThoiGianThanhToan` datetime NOT NULL DEFAULT current_timestamp(),
  `TongTien` double NOT NULL,
  `ChietKhau` double NOT NULL,
  `TinhTrang` bit(1) NOT NULL DEFAULT b'0',
  `MaKhachHang` int(11) DEFAULT NULL,
  `MaHoiVien` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaOrder` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaHoaDon`),
  KEY `FK_HoaDon_PhieuGoiMon` (`MaOrder`),
  KEY `FK_HoaDon_KhacHang` (`MaKhachHang`),
  KEY `FK_HoaDon_HoiVien` (`MaHoiVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`MaHoaDon`, `ThoiGianThanhToan`, `TongTien`, `ChietKhau`, `TinhTrang`, `MaKhachHang`, `MaHoiVien`, `MaOrder`) VALUES
('HD001', '2021-12-26 17:14:08', 600000, 0, b'1', NULL, NULL, 'PGM002'),
('HD002', '2021-12-27 23:23:56', 300000, 0, b'1', NULL, '159', 'PGM004'),
('HD003', '2021-12-27 23:26:46', 300000, 0, b'1', NULL, NULL, 'PGM005'),
('HD004', '2021-12-27 23:26:55', 300000, 0, b'1', NULL, '159', 'PGM006'),
('HD005', '2021-12-28 10:15:17', 300000, 0, b'1', NULL, NULL, 'PGM007'),
('HD006', '2021-12-28 10:15:25', 300000, 0, b'1', NULL, '159', 'PGM008'),
('HD007', '2021-12-28 10:16:27', 430000, 0, b'1', NULL, '159', 'PGM010'),
('HD008', '2021-12-28 10:21:56', 300000, 0, b'1', NULL, NULL, 'PGM011'),
('HD009', '2021-12-28 10:22:01', 300000, 0, b'1', NULL, '159', 'PGM012'),
('HD010', '2022-01-05 14:02:58', 460000, 0, b'1', NULL, NULL, 'PGM013'),
('HD011', '2022-01-07 04:52:48', 300000, 0, b'1', NULL, '159', 'PGM014');

-- --------------------------------------------------------

--
-- Table structure for table `hoivien`
--

DROP TABLE IF EXISTS `hoivien`;
CREATE TABLE IF NOT EXISTS `hoivien` (
  `TenHoiVien` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `TongSoTienThanhToan` double NOT NULL,
  `Email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `QuyenLoi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SoDienThoai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `hoivien`
--

INSERT INTO `hoivien` (`TenHoiVien`, `SoDienThoai`, `TongSoTienThanhToan`, `Email`, `DiaChi`, `QuyenLoi`) VALUES
('Tăng Kiến Trung', '0159987654', 1, '', '', 'không có'),
('Trung', '159', 1630000, '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
CREATE TABLE IF NOT EXISTS `khachhang` (
  `MaKhachHang` int(11) NOT NULL AUTO_INCREMENT,
  `SoDienThoai` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `TenKhachHang` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaKhachHang`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MaKhachHang`, `SoDienThoai`, `TenKhachHang`, `DiaChi`, `Email`) VALUES
(21, '0344883919', 'Nguyễn Trường Anh', '202/3E, Huỳnh Văn Bánh, Quận 11, Tp.HCM', 'truonganh@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai`
--

DROP TABLE IF EXISTS `khuyenmai`;
CREATE TABLE IF NOT EXISTS `khuyenmai` (
  `MaKM` int(11) NOT NULL AUTO_INCREMENT,
  `TenChuongTrinh` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `MoTa` text COLLATE utf8_unicode_ci NOT NULL,
  `GiaTriKhuyenMai` int(11) NOT NULL,
  `NgayBatDau` date NOT NULL,
  `NgayKetThuc` date NOT NULL,
  PRIMARY KEY (`MaKM`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `khuyenmai`
--

INSERT INTO `khuyenmai` (`MaKM`, `TenChuongTrinh`, `MoTa`, `GiaTriKhuyenMai`, `NgayBatDau`, `NgayKetThuc`) VALUES
(2, 'Tết ', 'Khuyến mãi tết', 5, '2022-01-20', '2022-01-28'),
(3, 'Giáng sinh', 'Khuyến mãi giáng sinh cùng ông già Noel', 5, '2022-01-01', '2022-03-31'),
(4, 'Hè', 'Khuyến mãi hè cho trẻ em', 10, '2022-01-01', '2022-03-31');


-- --------------------------------------------------------

--
-- Table structure for table `lichhen`
--

DROP TABLE IF EXISTS `lichhen`;
CREATE TABLE IF NOT EXISTS `lichhen` (
  `MaLichHen` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `NhuCau` varchar(600) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoLuongKhach` int(11) NOT NULL,
  `ThoiGian` time NOT NULL,
  `NgayHen` date NOT NULL,
  `TinhTrang` bit(1) DEFAULT b'0',
  `MaKhachHang` int(11) NOT NULL,
  `MaNhanVien` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaBanAn` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaLichHen`),
  KEY `FK_LichHen_NhanVien` (`MaNhanVien`),
  KEY `FK_LichHen_BanAn` (`MaBanAn`),
  KEY `FK_LichHen_KhachHang` (`MaKhachHang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `lichhen`
--

INSERT INTO `lichhen` (`MaLichHen`, `NhuCau`, `SoLuongKhach`, `ThoiGian`, `NgayHen`, `TinhTrang`, `MaKhachHang`, `MaNhanVien`, `MaBanAn`) VALUES
('LH001', 'Ăn cưới', 10, '13:13:11', '2021-12-09', b'1', 21, 'NV001', 'BA003');

-- --------------------------------------------------------

--
-- Table structure for table `monan`
--

DROP TABLE IF EXISTS `monan`;
CREATE TABLE IF NOT EXISTS `monan` (
  `MaMonAn` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenMonAn` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DonGia` double NOT NULL,
  `DonVi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MoTa` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `MoTaNgan` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `HinhAnh` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `MaNhom` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaMonAn`),
  KEY `FK_MonAn_NhomMonAn` (`MaNhom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `monan`
--

INSERT INTO `monan` (`MaMonAn`, `TenMonAn`, `DonGia`, `DonVi`, `MoTa`, `MoTaNgan`, `HinhAnh`, `MaNhom`) VALUES
('MA001', 'Lẩu thái', 300000, 'VNĐ', 'Lẩu được nấu từ các nguyên vật liệu tươi ngon nhất, được chọn lọc trong ngày, tạo nên nước lẩu thơm ', 'Phù hợp cho 2-3 người ăn. Nước lẩu đậm vị, thơm ngon.', 'lau1.jpg', 'NMA001'),
('MA002', 'Lẩu cua đồng', 350000, 'VNĐ', 'Lẩu cua đồng, thơm ngon, được nấu từ cua đồng giao trong ngày.', 'Phù hợp cho 2-3 người ăn.', 'lau2.jpg', 'NMA001'),
('MA003', 'Lẩu dê', 300000, 'VNĐ', 'Lẩu được hầm trong hơn 24h, các nguyên liệu được chọn lọc từ các chú dê núi, đảm bảo hương vị thơm n', 'Phù hợp cho 2-3 người ăn.', 'lau3.jpg', 'NMA001'),
('MA004', 'Lẩu bò', 350000, 'VNĐ', 'Lẩu bò thơm ngon, nước hầm đậm vị, được ninh từ xương bò với thời gian hơn 24h.', 'Phù hợp cho 2-3 người ăn.', 'lau4.jpg', 'NMA001'),
('MA005', 'Lẩu mắm', 300000, 'VNĐ', 'Lẩu mắm thơm ngon tuyệt vời.', 'Phù hợp cho 2-3 người ăn.', 'lau5.jpg', 'NMA001'),
('MA006', 'Lẩu gà', 300000, 'VNĐ', 'Lẩu gà thơm ngon, bổ dưỡng, rất phù hợp cho người cần tẩm bổ.', 'Phù hợp cho 2-3 người ăn.', 'lau6.jpg', 'NMA001'),
('MA007', 'Lẩu nấm', 350000, 'VNĐ', 'Lẩu nấm với nước dùng ngọt ngào, nấm giòn và tươi.', 'Phù hợp cho 2-3 người ăn.', 'lau7.jpg', 'NMA001'),
('MA008', 'Lẩu cá kèo', 350000, 'VNĐ', 'Cá kèo được chọn lọc và sử dụng trong ngày, đảm bảo độ tươi ngon và chắc thịt.', 'Phù hợp cho 2-3 người ăn.', 'lau8.jpg', 'NMA001'),
('MA009', 'Lẩu cá thác lác', 300000, 'VNĐ', 'Nước lẩu ngọt, đậm vị, cá thác lác tươi ngon.', 'Phù hợp cho 2-3 người ăn.', 'lau9.jpg', 'NMA001'),
('MA010', 'Lẩu bò nhúng dấm', 350000, 'VNĐ', 'Bò nhúng dấm thơm ngon, thịt bò tươi mềm.', 'Phù hợp cho 2-3 người ăn.', 'lau10.jpg', 'NMA001'),
('MA011', 'Cút nướng', 100000, 'VNĐ', 'Cút nướng thơm ngon, nhấm mồi hết ý.', 'Một suất gồm 4 con cút nướng.', 'nuong1.jpg', 'NMA002'),
('MA012', 'Gà nướng', 220000, 'VNĐ', 'Gà nướng nguyên con, đậm vị.', 'Gà nướng với khối lượng 1.5kg/con.', 'nuong2.jpg', 'NMA002'),
('MA013', 'Tôm nướng muối ớt', 100000, 'VNĐ', 'Thịt tôm chắc, tươi ngon, kết hợp với muối ớt cay nồng tạo hương vị lôi cuốn.', 'Mỗi phần gồm 8 con tôm.', 'nuong3.jpg', 'NMA002'),
('MA014', 'Bạch tuộc nướng', 350000, 'VNĐ', 'Bạch tuộc được chế biến tươi sống, đảm bảo chất lượng thơm ngon.', 'Phần gồm 1 con bạch tuộc to.', 'nuong4.jpg', 'NMA002'),
('MA015', 'Thịt xiên nướng', 50000, 'VNĐ', 'Thịt xiên kết hợp rau củ mang đến hương vị thơm ngon, cân bằng giữa thịt và rau.', 'Mỗi phần gồm 2 xiên thịt.', 'nuong5.jpg', 'NMA002'),
('MA016', 'Chân gà nướng', 75000, 'VNĐ', 'Chân gà nướng thấm vị, thích hợp cho nhâm nhi với bia.', 'Mỗi phần gồm 4 chân gà.', 'nuong6.jpg', 'NMA002'),
('MA017', 'Sườn nướng', 150000, 'VNĐ', 'Sườn nướng chuẩn vị BBQ, ngon ngỡ ngàn.\r\n', 'Mỗi phần gồm 2 cây sườn đậm vị.', 'nuong7.jpg', 'NMA002'),
('MA018', 'Cá lóc nướng', 230000, 'VNĐ', 'Được chế biến từ nguyên liệu tươi sống, giữ nguyên hương vị ngọt của thịt cá.', 'Mỗi phần gồm 1 con cá 1,5kg và rau ăn kèm', 'nuong8.jpg', 'NMA002'),
('MA019', 'Gỏi gà xé phay', 60000, 'VNĐ', 'Nguyên liệu tươi ngon, thịt gà chắc và ngọt.', 'Phù hợp cho 1-2 người ăn.', 'goi1.jpg', 'NMA003'),
('MA020', 'Nộm hoa chuối bắp bò', 75000, 'VNĐ', 'Bắp bò ngon kết hợp với hoa chuối tươi tạo nên một món ăn chuẩn ngon.', 'Phù hợp cho 1-2 người ăn.', 'goi2.jpg', 'NMA003'),
('MA021', 'Gỏi đu đủ tai heo - tôm', 60000, 'VNĐ', 'Tai heo giai ngon kết hơp với đu đủ giòn giòn và thịt tôm ngọt, chắc thịt.', 'Phù hợp cho 1-2 người ăn.', 'goi3.jpg', 'NMA003'),
('MA022', 'Gỏi càng cua thịt bò', 60000, 'VNĐ', 'Rau càng cua chua ngọt kết hợp với thịt bò mềm ngon.', 'Phù hợp cho 1-2 người ăn.', 'goi4.jpg', 'NMA003'),
('MA023', 'Gỏi ngó sen tôm thịt', 60000, 'VNĐ', 'Thơm ngon chuẩn vị.', 'Phù hợp cho 1-2 người ăn.', 'goi5.jpg', 'NMA003'),
('MA024', 'Nộm củ hủ dừa', 60000, 'VNĐ', 'Vị giòn của củ hủ dừa kết hợp với thịt tôm tươi tạo nên vị ngon tinh tế.', 'Phù hợp cho 1-2 người ăn.', 'goi6.jpg', 'NMA003'),
('MA025', 'Gỏi cuốn tôm thịt', 70000, 'VNĐ', 'Gỏi cuốn thơm ngon kết hợp hài hòa giữa thịt heo cùng tôm, bún và rau, đi đôi với nước chấm tương đe', 'Phần ăn gồm 10 cuốn.', 'goi7.jpg', 'NMA003'),
('MA026', 'Gỏi cuốn bì', 60000, 'VNĐ', 'Bì giòn ngon kết hợp với nước mắm mặn ngọt tạo nên hương vị tuyệt vời.', 'Mỗi phần gồm 10 cuốn.', 'goi8.jpg', 'NMA003'),
('MA027', 'Nem nướng cuốn bánh hỏi', 100000, 'VNĐ', 'Nem nướng thơm ngon chuẩn bị, cuốn cùng bánh hỏi kèm thêm rau, chấm nước mắm chua ngọt tạo hương vị ', 'Phù hợp cho 2-3 người ăn.', 'cuon1.jpg', 'NMA004'),
('MA028', 'Thịt nướng cuốn bánh tráng', 100000, 'VNĐ', 'Thịt nướng thơm ngon, cuốn với bánh tráng và rau, chấm vào nước mắm tạo hương vị cực đỉnh.', 'Phù hợp cho 2-3 người ăn.', 'cuon2.jpg', 'NMA004'),
('MA029', 'Bánh xèo tôm thịt', 50000, 'VNĐ', 'Bánh xèo mềm giai, cùng với hương thơm của thịt và tôm.', 'Phù hợp cho 1-2 người ăn.', 'cuon3.jpg', 'NMA004'),
('MA030', 'Chả giò tôm thịt', 50000, 'VNĐ', 'Chả giò vỏ giòn bên ngoài, kết hợp với thịt heo và tôm bằm nhuyễn bên trong, tạo hương vị thơm ngon.', 'Mỗi phần gồm 6 chiếc chả giò.', 'cuon4.jpg', 'NMA004'),
('MA031', 'Bò bía mặn', 50000, 'VNĐ', 'Sự kết hợp hài hòa giữa bún, rau và lạp xưởng, chấm với nước chấm đặc biệt của quan tạo hương vị tuy', 'Mỗi phần gồm 15 chiếc.', 'cuon5.jpg', 'NMA004'),
('MA032', 'Thịt luộc cuốn bánh tráng', 70000, 'VNĐ', 'Thịt luộc ngon kết hợp với bún và rau, chấm với nước mắm mang lại hương vị bùng nổ.', 'Phù hợp cho 2-3 người ăn.', 'cuon6.jpg', 'NMA004'),
('MA033', 'Khoai tây chiên', 30000, 'VNĐ', 'Khoai tây từ Đà Lạt, đảm bảo vị ngon khi thưởng thức.', 'Phù hợp 1-2 người ăn.', 'annhe1.jpg', 'NMA005'),
('MA034', 'Cá viên chiên', 30000, 'VNĐ', 'Cá viên chiên đủ loại.', 'Phù hợp cho 1-2 người ăn.', 'annhe2.jpg', 'NMA005'),
('MA035', 'Gà rán', 50000, 'VNĐ', 'Gà rán giòn ngon.', 'Mỗi phần gồm 2 đuồi.', 'annhe3.jpg', 'NMA005'),
('MA036', 'Chuối chiên', 20000, 'VNĐ', 'Chuối chiên vỏ giòn, chuối ngọt bên trong.', 'Mỗi phần gồm 4 trái.', 'annhe4.jpg', 'NMA005'),
('MA037', 'Cánh gà chiên nước mắm', 75000, 'VNĐ', 'Cánh gà chiên giòn kết hợp với vị nước mắm đậm đà, ngọt ngọt, cay cay, tạo nên hương vị tuyệt vời.', 'Mỗi phần gồm 4 cánh.', 'annhe5.jpg', 'NMA005');

-- --------------------------------------------------------

--
-- Table structure for table `nguyenvatlieu`
--

DROP TABLE IF EXISTS `nguyenvatlieu`;
CREATE TABLE IF NOT EXISTS `nguyenvatlieu` (
  `MaNguyenVatLieu` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenNguyenVatLieu` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DonGia` double NOT NULL,
  `DonVi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TinhTrang` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuongTonKho` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`MaNguyenVatLieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nguyenvatlieu`
--

INSERT INTO `nguyenvatlieu` (`MaNguyenVatLieu`, `TenNguyenVatLieu`, `DonGia`, `DonVi`, `TinhTrang`, `SoLuongTonKho`) VALUES
('NVL001', 'Bàn ăn 4 chỗ', 100000, 'Chiếc', 'New 99%', 0),
('NVL002', 'Muỗng ăn bằng bạc', 200000, 'Chiếc', 'Bạc fake chợ bà chiễu', 0),
('NVL003', 'Nồi đất đựng lẩu', 100000, 'Nồi', 'New 100% mới nung', 0),
('NVL004', 'Xiên nướng thịt', 30000, 'Xiên', 'New 100%', 0),
('NVL005', 'Gà nguyên con', 350000, 'Con', 'Gà nguyên con làm sạch tươi', 0),
('NVL006', 'Bò phi lê', 150000, 'kg', 'Thịt bò phi lê mềm ngọt mọng nước', 0),
('NVL007', 'Dú dê', 300000, 'Cái', 'Dú dê tươi dùng làm dú dê nướng', 0),
('NVL008', 'Cá lóc', 150000, 'Con', 'Cá lóc tươi sống', 0);

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
CREATE TABLE IF NOT EXISTS `nhacungcap` (
  `MaNhaCungCap` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MoTaNhaCungCap` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenNhaCungCap` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaNhaCungCap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
CREATE TABLE IF NOT EXISTS `nhanvien` (
  `MaNhanVien` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenNhanVien` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Luong` double NOT NULL,
  `ChucVu` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNhanVien`, `TenNhanVien`, `SoDienThoai`, `Luong`, `ChucVu`, `DiaChi`, `Email`) VALUES
('NV001', 'Tăng Kiến Trung', '0123456789', 1000000000, 'Quản Lý Nhà Hàng', '20/10/1 Trần Định Trọng Huyện Ba Đình Hà Nội', '51900718@student.tdtu.edu.vn'),
('NV004', 'Nguyễn Võ Hoàng Vũ', '0456', 0, 'Thu Ngân', 'abc', 'vu@gmail.com'),
('NV005', 'Nguyễn Trường Anh', '0123', 0, 'Lễ Tân', 'abc', 'anh@gmail.com'),
('NV006', 'Trương Thịnh', '0789', 0, 'Phục Vụ', 'abc', 'thinh@gmail.com'),
('NV007', 'Phan Thái An', ' 0989898989', 1000000000, 'Quản Lý Kho', 'Bến Tre', 'thaianniee@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `nhommonan`
--

DROP TABLE IF EXISTS `nhommonan`;
CREATE TABLE IF NOT EXISTS `nhommonan` (
  `MaNhom` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenNhom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaNhom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhommonan`
--

INSERT INTO `nhommonan` (`MaNhom`, `TenNhom`) VALUES
('NMA001', 'Lẩu'),
('NMA002', 'Nướng'),
('NMA003', 'Gỏi'),
('NMA004', 'Cuốn'),
('NMA005', 'Ăn nhẹ');

-- --------------------------------------------------------

--
-- Table structure for table `phieugoimon`
--

DROP TABLE IF EXISTS `phieugoimon`;
CREATE TABLE IF NOT EXISTS `phieugoimon` (
  `MaOrder` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `GhiChuMonAn` varchar(600) COLLATE utf8_unicode_ci NOT NULL,
  `MaBanAn` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaNhanVien` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaOrder`),
  KEY `FK_PhieuGoiMon_BanAn` (`MaBanAn`),
  KEY `FK_PhieuGoiMon_NhanVien` (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `phieugoimon`
--

INSERT INTO `phieugoimon` (`MaOrder`, `GhiChuMonAn`, `MaBanAn`, `MaNhanVien`) VALUES
('PGM001', '', 'BA002', 'NV001'),
('PGM002', '', 'BA002', 'NV001'),
('PGM003', '', 'BA003', 'NV001'),
('PGM004', '', 'BA008', 'NV001'),
('PGM005', '', 'BA012', 'NV001'),
('PGM006', '', 'BA012', 'NV001'),
('PGM007', '', 'BA003', 'NV001'),
('PGM008', '', 'BA002', 'NV001'),
('PGM009', '', 'BA006', 'NV001'),
('PGM010', '', 'BA010', 'NV001'),
('PGM011', '', 'BA002', 'NV001'),
('PGM012', '', 'BA003', 'NV001'),
('PGM013', '', 'BA003', 'NV001'),
('PGM014', '', 'BA003', 'NV001'),
('PGM015', '', 'BA003', 'NV001');

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

DROP TABLE IF EXISTS `phieunhap`;
CREATE TABLE IF NOT EXISTS `phieunhap` (
  `MaPhieuNhap` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `NgayNhapHang` datetime NOT NULL,
  `TongGiaNhap` double NOT NULL,
  `GhiChu` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `MaNhaCungCap` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaNhanVien` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaPhieuNhap`),
  KEY `FK_PhieuNhap_NhaCungCap` (`MaNhaCungCap`),
  KEY `FK_PhieuNhap_NhanVien` (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
CREATE TABLE IF NOT EXISTS `taikhoan` (
  `TenTaiKhoan` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MatKhau` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `MaNhanVien` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`TenTaiKhoan`),
  KEY `FK_TaiKhoan_NhanVien` (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`TenTaiKhoan`, `MatKhau`, `MaNhanVien`) VALUES
('admin', '$2a$06$quSQWNFP4nBh3Q/OPHrw8.0rlTOxwX2bV.2KzD0hgTNo2ArVK6/lC', 'NV001'),
('anh@gmail.com', '$2a$06$2DB.nd2gO6fGg5peWVYYDeeRTNTyFDci2X3T6P.HmXJl9Bpkb.kR6', 'NV005'),
('thaianniee@gmail.com', '$2a$06$PJ4bR7y7OohduUx4dqxLTuu2kAQyTGwFIRGNfGVu4pNgRH6.jpWLq', 'NV007'),
('thinh@gmail.com', '$2a$06$Db2WaNVeIYcCEUbuBTjJFuoPF7k90mPdc4xKBbCs3FrYmtF02i56i', 'NV006'),
('vu@gmail.com', '$2a$06$3mPV0uUqJF6REjc.LYX3h.zbbzsfHwdr.H2BGTeIqF3PBWCBuR0/2', 'NV004');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitietgoimon`
--
ALTER TABLE `chitietgoimon`
  ADD CONSTRAINT `FK_ChiTietGoiMon_MonAn` FOREIGN KEY (`MaMonAn`) REFERENCES `monan` (`MaMonAn`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_ChiTietGoiMon_PhieuGoiMon` FOREIGN KEY (`MaOrder`) REFERENCES `phieugoimon` (`MaOrder`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `FK_ChiTietPhieuNhap_NguyenVatLieu` FOREIGN KEY (`MaNguyenVatLieu`) REFERENCES `nguyenvatlieu` (`MaNguyenVatLieu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_ChiTietPhieuNhap_PhieuNhap` FOREIGN KEY (`MaPhieuNhap`) REFERENCES `phieunhap` (`MaPhieuNhap`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `FK_HoaDon_HoiVien` FOREIGN KEY (`MaHoiVien`) REFERENCES `hoivien` (`SoDienThoai`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_HoaDon_KhacHang` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKhachHang`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_HoaDon_PhieuGoiMon` FOREIGN KEY (`MaOrder`) REFERENCES `phieugoimon` (`MaOrder`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `lichhen`
--
ALTER TABLE `lichhen`
  ADD CONSTRAINT `FK_LichHen_BanAn` FOREIGN KEY (`MaBanAn`) REFERENCES `banan` (`MaBanAn`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_LichHen_KhachHang` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKhachHang`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_LichHen_NhanVien` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `monan`
--
ALTER TABLE `monan`
  ADD CONSTRAINT `FK_MonAn_NhomMonAn` FOREIGN KEY (`MaNhom`) REFERENCES `nhommonan` (`MaNhom`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `phieugoimon`
--
ALTER TABLE `phieugoimon`
  ADD CONSTRAINT `FK_PhieuGoiMon_BanAn` FOREIGN KEY (`MaBanAn`) REFERENCES `banan` (`MaBanAn`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PhieuGoiMon_NhanVien` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `FK_PhieuNhap_NhaCungCap` FOREIGN KEY (`MaNhaCungCap`) REFERENCES `nhacungcap` (`MaNhaCungCap`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PhieuNhap_NhanVien` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_TaiKhoan_NhanVien` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
