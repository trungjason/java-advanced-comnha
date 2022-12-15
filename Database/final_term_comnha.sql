-- phpMyAdmin SQL Dump
<<<<<<< Updated upstream
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 14, 2022 lúc 03:20 PM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.1.2
=======
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2022 at 03:05 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6
>>>>>>> Stashed changes

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
<<<<<<< Updated upstream
-- Cơ sở dữ liệu: `final_term_comnha`
=======
-- Database: `final_term_comnha`
>>>>>>> Stashed changes
--
CREATE DATABASE IF NOT EXISTS `final_term_comnha` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `final_term_comnha`;

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `ban_an`
--

CREATE TABLE IF NOT EXISTS `ban_an` (
  `ma_ban_an` bigint(20) NOT NULL AUTO_INCREMENT,
  `ghi_chu` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `suc_chua` int(11) NOT NULL,
  `tinh_trang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ma_ban_an`)
=======
-- Table structure for table `ban_an`
--

CREATE TABLE `ban_an` (
  `ma_ban_an` bigint(20) NOT NULL,
  `ghi_chu` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `suc_chua` int(11) NOT NULL,
  `tinh_trang` varchar(255) COLLATE utf8_unicode_ci NOT NULL
>>>>>>> Stashed changes
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `chi_tiet_goi_mon`
--

CREATE TABLE IF NOT EXISTS `chi_tiet_goi_mon` (
=======
-- Table structure for table `chi_tiet_goi_mon`
--

CREATE TABLE `chi_tiet_goi_mon` (
>>>>>>> Stashed changes
  `ma_mon_an` bigint(20) NOT NULL,
  `ma_order` bigint(20) NOT NULL,
  `so_luong_mon_an` int(11) DEFAULT NULL,
  `chi_tiet_goi_mon_mon_an_foreign_key` bigint(20) NOT NULL,
<<<<<<< Updated upstream
  `chi_tiet_goi_mon_phieu_goi_mon_foreign_key` bigint(20) NOT NULL,
  PRIMARY KEY (`ma_mon_an`,`ma_order`),
  KEY `FKeu5wgck2psgr77w3fy974sgcj` (`chi_tiet_goi_mon_mon_an_foreign_key`),
  KEY `FKiwdnsh8umb13utgwnh3iolk43` (`chi_tiet_goi_mon_phieu_goi_mon_foreign_key`)
=======
  `chi_tiet_goi_mon_phieu_goi_mon_foreign_key` bigint(20) NOT NULL
>>>>>>> Stashed changes
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `chi_tiet_phieu_nhap`
--

CREATE TABLE IF NOT EXISTS `chi_tiet_phieu_nhap` (
=======
-- Table structure for table `chi_tiet_phieu_nhap`
--

CREATE TABLE `chi_tiet_phieu_nhap` (
>>>>>>> Stashed changes
  `ma_nguyen_vat_lieu` bigint(20) NOT NULL,
  `ma_phieu_nhap` bigint(20) NOT NULL,
  `so_luong_nhap` int(11) DEFAULT NULL,
  `chi_tiet_phieu_nhap_nguyen_vat_lieu_foreign_key` bigint(20) NOT NULL,
<<<<<<< Updated upstream
  `chi_tiet_phieu_nhap_phieu_nhap_foreign_key` bigint(20) NOT NULL,
  PRIMARY KEY (`ma_nguyen_vat_lieu`,`ma_phieu_nhap`),
  KEY `FKt9ierohgo7p5i8bjjw6wi3jlh` (`chi_tiet_phieu_nhap_nguyen_vat_lieu_foreign_key`),
  KEY `FK9reox427sx0pctkrnaqc3e9v1` (`chi_tiet_phieu_nhap_phieu_nhap_foreign_key`)
=======
  `chi_tiet_phieu_nhap_phieu_nhap_foreign_key` bigint(20) NOT NULL
>>>>>>> Stashed changes
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `hoa_don`
--

CREATE TABLE IF NOT EXISTS `hoa_don` (
  `ma_hoa_don` bigint(20) NOT NULL AUTO_INCREMENT,
=======
-- Table structure for table `hoa_don`
--

CREATE TABLE `hoa_don` (
  `ma_hoa_don` bigint(20) NOT NULL,
>>>>>>> Stashed changes
  `chiet_khau` double DEFAULT NULL,
  `thoi_gian_thanh_toan` datetime NOT NULL,
  `tinh_trang` int(11) NOT NULL,
  `tong_tien` double NOT NULL,
  `hoa_don_hoi_vien_foreign_key` bigint(20) DEFAULT NULL,
  `hoa_don_khach_hang_foreign_key` bigint(20) DEFAULT NULL,
  `hoa_don_nhan_vien_foreign_key` bigint(20) DEFAULT NULL,
<<<<<<< Updated upstream
  `hoa_don_phieu_goi_mon_foreign_key` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ma_hoa_don`),
  KEY `FKkj71losh6g82surdsuq4hvwbe` (`hoa_don_hoi_vien_foreign_key`),
  KEY `FKdjptraaljhen8wta139df905i` (`hoa_don_khach_hang_foreign_key`),
  KEY `FKtdrxkdky5udarvnab0bpob2om` (`hoa_don_nhan_vien_foreign_key`),
  KEY `FKdrhfku88af7h53ceom786sk7a` (`hoa_don_phieu_goi_mon_foreign_key`)
=======
  `hoa_don_phieu_goi_mon_foreign_key` bigint(20) DEFAULT NULL
>>>>>>> Stashed changes
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `hoi_vien`
--

CREATE TABLE IF NOT EXISTS `hoi_vien` (
  `ma_hoi_vien` bigint(20) NOT NULL AUTO_INCREMENT,
=======
-- Table structure for table `hoi_vien`
--

CREATE TABLE `hoi_vien` (
  `ma_hoi_vien` bigint(20) NOT NULL,
>>>>>>> Stashed changes
  `dia_chi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mat_khau` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `quyen_loi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `so_dien_thoai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ten_hoi_vien` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
<<<<<<< Updated upstream
  `tong_so_tien_thanh_toan` double DEFAULT NULL,
  PRIMARY KEY (`ma_hoi_vien`),
  UNIQUE KEY `UK_csook5xnk32xw1bokdpox0wdb` (`email`)
=======
  `tong_so_tien_thanh_toan` double DEFAULT NULL
>>>>>>> Stashed changes
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `khach_hang`
--

CREATE TABLE IF NOT EXISTS `khach_hang` (
  `ma_khach_hang` bigint(20) NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `so_dien_thoai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ten_khach_hang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ma_khach_hang`)
=======
-- Table structure for table `khach_hang`
--

CREATE TABLE `khach_hang` (
  `ma_khach_hang` bigint(20) NOT NULL,
  `dia_chi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `so_dien_thoai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ten_khach_hang` varchar(255) COLLATE utf8_unicode_ci NOT NULL
>>>>>>> Stashed changes
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `lich_hen`
--

CREATE TABLE IF NOT EXISTS `lich_hen` (
  `ma_lich_hen` bigint(20) NOT NULL AUTO_INCREMENT,
=======
-- Table structure for table `lich_hen`
--

CREATE TABLE `lich_hen` (
  `ma_lich_hen` bigint(20) NOT NULL,
>>>>>>> Stashed changes
  `ngay_hen` date DEFAULT NULL,
  `nhu_cau` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `so_luong_khach` int(11) DEFAULT NULL,
  `thoi_gian` datetime DEFAULT NULL,
  `tinh_trang` int(11) DEFAULT NULL,
  `lich_hen_ban_an_foreign_key` bigint(20) DEFAULT NULL,
  `lich_hen_khach_hang_foreign_key` bigint(20) NOT NULL,
<<<<<<< Updated upstream
  `lich_hen_nhan_vien_foreign_key` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ma_lich_hen`),
  KEY `FKiyed67v05cuosqg6rulawyqk3` (`lich_hen_ban_an_foreign_key`),
  KEY `FKsha0hjrgt2djgsnqp34k6nphc` (`lich_hen_khach_hang_foreign_key`),
  KEY `FKi2rwgpki1rq3n7xpaygrl5qn7` (`lich_hen_nhan_vien_foreign_key`)
=======
  `lich_hen_nhan_vien_foreign_key` bigint(20) DEFAULT NULL
>>>>>>> Stashed changes
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `mon_an`
--

CREATE TABLE IF NOT EXISTS `mon_an` (
  `ma_mon_an` bigint(20) NOT NULL AUTO_INCREMENT,
=======
-- Table structure for table `mon_an`
--

CREATE TABLE `mon_an` (
  `ma_mon_an` bigint(20) NOT NULL,
>>>>>>> Stashed changes
  `don_gia` double DEFAULT NULL,
  `don_vi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hinh_anh` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mo_ta` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mo_ta_ngan` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ten_mon_an` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
<<<<<<< Updated upstream
  `mon_an_nhom_mon_an_foreign_key` bigint(20) NOT NULL,
  PRIMARY KEY (`ma_mon_an`),
  KEY `FKmf0kjtq5ugc1ps7e08rjvaq3n` (`mon_an_nhom_mon_an_foreign_key`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `mon_an`
=======
  `mon_an_nhom_mon_an_foreign_key` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `mon_an`
>>>>>>> Stashed changes
--

INSERT INTO `mon_an` (`ma_mon_an`, `don_gia`, `don_vi`, `hinh_anh`, `mo_ta`, `mo_ta_ngan`, `ten_mon_an`, `mon_an_nhom_mon_an_foreign_key`) VALUES
(75, 300000, 'Nồi', 'lau1.jpg', 'Lẩu được nấu từ các nguyên vật liệu tươi ngon nhất, được chọn lọc trong ngày, tạo nên nước lẩu thơm ', 'Phù hợp cho 2-3 người ăn. Nước lẩu đậm vị, thơm ngon.', 'Lẩu thái', 1),
(112, 350000, 'Nồi', 'lau2.jpg', 'Lẩu cua đồng, thơm ngon, được nấu từ cua đồng giao trong ngày.', 'Phù hợp cho 2-3 người ăn.', 'Lẩu cua đồng', 1),
(113, 300000, 'Nồi', 'lau3.jpg', 'Lẩu được hầm trong hơn 24h, các nguyên liệu được chọn lọc từ các chú dê núi, đảm bảo hương vị thơm n', 'Phù hợp cho 2-3 người ăn.', 'Lẩu dê', 1),
(114, 350000, 'Nồi', 'lau4.jpg', 'Lẩu bò thơm ngon, nước hầm đậm vị, được ninh từ xương bò với thời gian hơn 24h.', 'Phù hợp cho 2-3 người ăn.', 'Lẩu bò', 1),
(115, 300000, 'Nồi', 'lau5.jpg', 'Lẩu mắm thơm ngon tuyệt vời.', 'Phù hợp cho 2-3 người ăn.', 'Lẩu mắm', 1),
(116, 300000, 'Nồi', 'lau6.jpg', 'Lẩu gà thơm ngon, bổ dưỡng, rất phù hợp cho người cần tẩm bổ.', 'Phù hợp cho 2-3 người ăn.', 'Lẩu gà', 1),
(117, 350000, 'Nồi', 'lau7.jpg', 'Lẩu nấm với nước dùng ngọt ngào, nấm giòn và tươi.', 'Phù hợp cho 2-3 người ăn.', 'Lẩu nấm', 1),
(118, 350000, 'Nồi', 'lau8.jpg', 'Cá kèo được chọn lọc và sử dụng trong ngày, đảm bảo độ tươi ngon và chắc thịt.', 'Phù hợp cho 2-3 người ăn.', 'Lẩu cá kèo', 1),
(119, 300000, 'Nồi', 'lau9.jpg', 'Nước lẩu ngọt, đậm vị, cá thác lác tươi ngon.', 'Phù hợp cho 2-3 người ăn.', 'Lẩu cá thác lác', 1),
(120, 350000, 'Nồi', 'lau10.jpg', 'Bò nhúng dấm thơm ngon, thịt bò tươi mềm.', 'Phù hợp cho 2-3 người ăn.', 'Lẩu bò nhúng dấm', 1),
(121, 100000, '4 Con', 'nuong1.jpg', 'Cút nướng thơm ngon, nhấm mồi hết ý.', 'Một suất gồm 4 con cút nướng.', 'Cút nướng', 2),
(122, 220000, '1 Con', 'nuong2.jpg', 'Gà nướng nguyên con, đậm vị.', 'Gà nướng với khối lượng 1.5kg/con.', 'Gà nướng', 2),
(123, 100000, '8 con', 'nuong3.jpg', 'Thịt tôm chắc, tươi ngon, kết hợp với muối ớt cay nồng tạo hương vị lôi cuốn.', 'Mỗi phần gồm 8 con tôm.', 'Tôm nướng muối ớt', 2),
(124, 350000, '1 con to', 'nuong4.jpg', 'Bạch tuộc được chế biến tươi sống, đảm bảo chất lượng thơm ngon.', 'Phần gồm 1 con bạch tuộc to.', 'Bạch tuộc nướng', 2),
(125, 50000, '2 xiên', 'nuong5.jpg', 'Thịt xiên kết hợp rau củ mang đến hương vị thơm ngon, cân bằng giữa thịt và rau.', 'Mỗi phần gồm 2 xiên thịt.', 'Thịt xiên nướng', 2),
(126, 75000, '4 chân gà', 'nuong6.jpg', 'Chân gà nướng thấm vị, thích hợp cho nhâm nhi với bia.', 'Mỗi phần gồm 4 chân gà.', 'Chân gà nướng', 2),
(127, 150000, '2 phần sườn', 'nuong7.jpg', 'Sườn nướng chuẩn vị BBQ, ngon ngỡ ngàn.\r\n', 'Mỗi phần gồm 2 cây sườn đậm vị.', 'Sườn nướng', 2),
(128, 230000, '1 con cá', 'nuong8.jpg', 'Được chế biến từ nguyên liệu tươi sống, giữ nguyên hương vị ngọt của thịt cá.', 'Mỗi phần gồm 1 con cá 1,5kg và rau ăn kèm', 'Cá lóc nướng', 2),
(129, 60000, '1 con gà', 'goi1.jpg', 'Nguyên liệu tươi ngon, thịt gà chắc và ngọt.', 'Phù hợp cho 1-2 người ăn.', 'Gỏi gà xé phay', 3),
(130, 75000, '1 phần', 'goi2.jpg', 'Bắp bò ngon kết hợp với hoa chuối tươi tạo nên một món ăn chuẩn ngon.', 'Phù hợp cho 1-2 người ăn.', 'Nộm hoa chuối bắp bò', 3),
(131, 60000, '1 phần', 'goi3.jpg', 'Tai heo giai ngon kết hơp với đu đủ giòn giòn và thịt tôm ngọt, chắc thịt.', 'Phù hợp cho 1-2 người ăn.', 'Gỏi đu đủ tai heo - tôm', 3),
(132, 60000, '1 phần', 'goi4.jpg', 'Rau càng cua chua ngọt kết hợp với thịt bò mềm ngon.', 'Phù hợp cho 1-2 người ăn.', 'Gỏi càng cua thịt bò', 3),
(133, 60000, '1 phần', 'goi5.jpg', 'Thơm ngon chuẩn vị.', 'Phù hợp cho 1-2 người ăn.', 'Gỏi ngó sen tôm thịt', 3),
(134, 60000, '1 phần', 'goi6.jpg', 'Vị giòn của củ hủ dừa kết hợp với thịt tôm tươi tạo nên vị ngon tinh tế.', 'Phù hợp cho 1-2 người ăn.', 'Nộm củ hủ dừa', 3),
(135, 70000, '10 cuốn', 'goi7.jpg', 'Gỏi cuốn thơm ngon kết hợp hài hòa giữa thịt heo cùng tôm, bún và rau, đi đôi với nước chấm tương đe', 'Phần ăn gồm 10 cuốn.', 'Gỏi cuốn tôm thịt', 3),
(136, 60000, '10 cuốn', 'goi8.jpg', 'Bì giòn ngon kết hợp với nước mắm mặn ngọt tạo nên hương vị tuyệt vời.', 'Mỗi phần gồm 10 cuốn.', 'Gỏi cuốn bì', 3),
(137, 100000, '6 nem', 'cuon1.jpg', 'Nem nướng thơm ngon chuẩn bị, cuốn cùng bánh hỏi kèm thêm rau, chấm nước mắm chua ngọt tạo hương vị ', 'Phù hợp cho 2-3 người ăn.', 'Nem nướng cuốn bánh hỏi', 4),
(138, 100000, '5 xiêng', 'cuon2.jpg', 'Thịt nướng thơm ngon, cuốn với bánh tráng và rau, chấm vào nước mắm tạo hương vị cực đỉnh.', 'Phù hợp cho 2-3 người ăn.', 'Thịt nướng cuốn bánh tráng', 4),
(139, 50000, '2 bánh', 'cuon3.jpg', 'Bánh xèo mềm giai, cùng với hương thơm của thịt và tôm.', 'Phù hợp cho 1-2 người ăn.', 'Bánh xèo tôm thịt', 4),
(140, 50000, '6 chiếc', 'cuon4.jpg', 'Chả giò vỏ giòn bên ngoài, kết hợp với thịt heo và tôm bằm nhuyễn bên trong, tạo hương vị thơm ngon.', 'Mỗi phần gồm 6 chiếc chả giò.', 'Chả giò tôm thịt', 4),
(141, 50000, 'VNĐ', 'cuon5.jpg', 'Sự kết hợp hài hòa giữa bún, rau và lạp xưởng, chấm với nước chấm đặc biệt của quan tạo hương vị tuy', 'Mỗi phần gồm 15 chiếc.', 'Bò bía mặn', 4),
(142, 70000, '1 phần', 'cuon6.jpg', 'Thịt luộc ngon kết hợp với bún và rau, chấm với nước mắm mang lại hương vị bùng nổ.', 'Phù hợp cho 2-3 người ăn.', 'Thịt luộc cuốn bánh tráng', 4),
(143, 30000, '1 phần', 'annhe1.jpg', 'Khoai tây từ Đà Lạt, đảm bảo vị ngon khi thưởng thức.', 'Phù hợp 1-2 người ăn.', 'Khoai tây chiên', 5),
(144, 30000, '1 phần', 'annhe2.jpg', 'Cá viên chiên đủ loại.', 'Phù hợp cho 1-2 người ăn.', 'Cá viên chiên', 5),
(145, 50000, '2 đùi', 'annhe3.jpg', 'Gà rán giòn ngon.', 'Mỗi phần gồm 2 đùi.', 'Gà rán', 5),
(146, 20000, '4 trái', 'annhe4.jpg', 'Chuối chiên vỏ giòn, chuối ngọt bên trong.', 'Mỗi phần gồm 4 trái.', 'Chuối chiên', 5),
(147, 75000, '4 cánh', 'annhe5.jpg', 'Cánh gà chiên giòn kết hợp với vị nước mắm đậm đà, ngọt ngọt, cay cay, tạo nên hương vị tuyệt vời.', 'Mỗi phần gồm 4 cánh.', 'Cánh gà chiên nước mắm', 5);

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `nguyen_vat_lieu`
--

CREATE TABLE IF NOT EXISTS `nguyen_vat_lieu` (
  `ma_nguyen_vat_lieu` bigint(20) NOT NULL AUTO_INCREMENT,
=======
-- Table structure for table `nguyen_vat_lieu`
--

CREATE TABLE `nguyen_vat_lieu` (
  `ma_nguyen_vat_lieu` bigint(20) NOT NULL,
>>>>>>> Stashed changes
  `don_gia` double DEFAULT NULL,
  `don_vi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `so_luong_ton_kho` int(11) DEFAULT NULL,
  `ten_nguyen_vat_lieu` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
<<<<<<< Updated upstream
  `tinh_trang` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ma_nguyen_vat_lieu`)
=======
  `tinh_trang` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
>>>>>>> Stashed changes
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `nhan_vien`
--

CREATE TABLE IF NOT EXISTS `nhan_vien` (
  `ma_nhan_vien` bigint(20) NOT NULL AUTO_INCREMENT,
=======
-- Table structure for table `nhan_vien`
--

CREATE TABLE `nhan_vien` (
  `ma_nhan_vien` bigint(20) NOT NULL,
>>>>>>> Stashed changes
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `anh_dai_dien` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `chuc_vu` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dia_chi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `luong` double DEFAULT NULL,
  `so_dien_thoai` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ten_nhan_vien` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
<<<<<<< Updated upstream
  `nhan_vien_tai_khoan_forgein_key` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ma_nhan_vien`),
  UNIQUE KEY `UK_12waxxsiniyddv2lt9ianfh8a` (`email`),
  KEY `FKmsg6em0vf83n4ymqlg65qcu83` (`nhan_vien_tai_khoan_forgein_key`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhan_vien`
--

INSERT INTO `nhan_vien` (`ma_nhan_vien`, `email`, `anh_dai_dien`, `chuc_vu`, `dia_chi`, `luong`, `so_dien_thoai`, `ten_nhan_vien`, `nhan_vien_tai_khoan_forgein_key`) VALUES
(1, '51900718@student.tdtu.edu.vn', NULL, 'Quản Lý Nhà Hàng', '20/10/1 Trần Định Trọng Huyện Ba Đình Hà Nội', 1000000000, '0123456789', 'Tăng Kiến Trung', 1),
(2, 'vu@gmail.com', NULL, 'Thu Ngân', 'abc', 0, '0456', 'Nguyễn Võ Hoàng Vũ', 4),
(3, 'anh@gmail.com', NULL, 'Lễ Tân', 'abc', 0, '0123', 'Nguyễn Trường Anh', 2),
(4, 'thinh@gmail.com', NULL, 'Phục Vụ', 'abc', 0, '0789', 'Trương Thịnh', 3);
=======
  `nhan_vien_tai_khoan_forgein_key` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
>>>>>>> Stashed changes

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `nha_cung_cap`
--

CREATE TABLE IF NOT EXISTS `nha_cung_cap` (
  `ma_nha_cung_cap` bigint(20) NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mo_ta_nha_cung_cap` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `so_dien_thoai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ten_nha_cung_cap` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ma_nha_cung_cap`),
  UNIQUE KEY `UK_cuf78wlhewgijvkdhpyo1woi5` (`so_dien_thoai`)
=======
-- Table structure for table `nha_cung_cap`
--

CREATE TABLE `nha_cung_cap` (
  `ma_nha_cung_cap` bigint(20) NOT NULL,
  `dia_chi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mo_ta_nha_cung_cap` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `so_dien_thoai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ten_nha_cung_cap` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
>>>>>>> Stashed changes
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `nhom_mon_an`
--

CREATE TABLE IF NOT EXISTS `nhom_mon_an` (
  `ma_nhom` bigint(20) NOT NULL AUTO_INCREMENT,
  `mo_ta` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ten_nhom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ma_nhom`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhom_mon_an`
=======
-- Table structure for table `nhom_mon_an`
--

CREATE TABLE `nhom_mon_an` (
  `ma_nhom` bigint(20) NOT NULL,
  `mo_ta` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ten_nhom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhom_mon_an`
>>>>>>> Stashed changes
--

INSERT INTO `nhom_mon_an` (`ma_nhom`, `mo_ta`, `ten_nhom`) VALUES
(1, 'Lẩu ngon', 'Lẩu'),
(2, 'Nướng ngon', 'Nướng'),
(3, 'Gỏi ngon', 'Gỏi'),
(4, 'Cuốn ngon', 'Cuốn'),
(5, 'Ăn nhẹ ngon', 'Ăn nhẹ');

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `phieu_goi_mon`
--

CREATE TABLE IF NOT EXISTS `phieu_goi_mon` (
  `ma_order` bigint(20) NOT NULL AUTO_INCREMENT,
  `ghi_chu_mon_an` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phieu_goi_mon_ban_an_foreign_key` bigint(20) NOT NULL,
  `phieu_goi_mon_nhan_vien_foreign_key` bigint(20) NOT NULL,
  PRIMARY KEY (`ma_order`),
  KEY `FKgo2b1hq7d3xn97drkuxi6g6mr` (`phieu_goi_mon_ban_an_foreign_key`),
  KEY `FKfkcxq772ghp2j9ghmgty3d4k` (`phieu_goi_mon_nhan_vien_foreign_key`)
=======
-- Table structure for table `phieu_goi_mon`
--

CREATE TABLE `phieu_goi_mon` (
  `ma_order` bigint(20) NOT NULL,
  `ghi_chu_mon_an` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phieu_goi_mon_ban_an_foreign_key` bigint(20) NOT NULL,
  `phieu_goi_mon_nhan_vien_foreign_key` bigint(20) NOT NULL
>>>>>>> Stashed changes
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `phieu_nhap`
--

CREATE TABLE IF NOT EXISTS `phieu_nhap` (
  `ma_phieu_nhap` bigint(20) NOT NULL AUTO_INCREMENT,
=======
-- Table structure for table `phieu_nhap`
--

CREATE TABLE `phieu_nhap` (
  `ma_phieu_nhap` bigint(20) NOT NULL,
>>>>>>> Stashed changes
  `ghi_chu` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngay_nhap_hang` datetime DEFAULT NULL,
  `tong_gia_nhap` double DEFAULT NULL,
  `phieu_nhap_nha_cung_cap_foreign_key` bigint(20) NOT NULL,
<<<<<<< Updated upstream
  `phieu_nhap_nhan_vien_foreign_key` bigint(20) NOT NULL,
  PRIMARY KEY (`ma_phieu_nhap`),
  KEY `FKeka7cj4vbauopbxtu5eqk4bwr` (`phieu_nhap_nha_cung_cap_foreign_key`),
  KEY `FKkcney1svi8wveoh66fcemv1i3` (`phieu_nhap_nhan_vien_foreign_key`)
=======
  `phieu_nhap_nhan_vien_foreign_key` bigint(20) NOT NULL
>>>>>>> Stashed changes
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
<<<<<<< Updated upstream
-- Cấu trúc bảng cho bảng `tai_khoan`
--

CREATE TABLE IF NOT EXISTS `tai_khoan` (
  `ma_tai_khoan` bigint(20) NOT NULL AUTO_INCREMENT,
  `mat_khau` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ten_tai_khoan` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ma_tai_khoan`),
  UNIQUE KEY `UK_qwwhjimkf21qn85a1cb8hanf9` (`ten_tai_khoan`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tai_khoan`
--

INSERT INTO `tai_khoan` (`ma_tai_khoan`, `mat_khau`, `ten_tai_khoan`) VALUES
(1, '$2a$06$quSQWNFP4nBh3Q/OPHrw8.0rlTOxwX2bV.2KzD0hgTNo2ArVK6/lC', 'admin'),
(2, '$2a$06$2DB.nd2gO6fGg5peWVYYDeeRTNTyFDci2X3T6P.HmXJl9Bpkb.kR6', 'anh@gmail.com'),
(3, '$2a$06$Db2WaNVeIYcCEUbuBTjJFuoPF7k90mPdc4xKBbCs3FrYmtF02i56i', 'thinh@gmail.com'),
(4, '$2a$06$3mPV0uUqJF6REjc.LYX3h.zbbzsfHwdr.H2BGTeIqF3PBWCBuR0/2', 'vu@gmail.com');

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chi_tiet_goi_mon`
=======
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `ma_tai_khoan` bigint(20) NOT NULL,
  `mat_khau` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ten_tai_khoan` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ban_an`
--
ALTER TABLE `ban_an`
  ADD PRIMARY KEY (`ma_ban_an`);

--
-- Indexes for table `chi_tiet_goi_mon`
--
ALTER TABLE `chi_tiet_goi_mon`
  ADD PRIMARY KEY (`ma_mon_an`,`ma_order`),
  ADD KEY `FKeu5wgck2psgr77w3fy974sgcj` (`chi_tiet_goi_mon_mon_an_foreign_key`),
  ADD KEY `FKiwdnsh8umb13utgwnh3iolk43` (`chi_tiet_goi_mon_phieu_goi_mon_foreign_key`);

--
-- Indexes for table `chi_tiet_phieu_nhap`
--
ALTER TABLE `chi_tiet_phieu_nhap`
  ADD PRIMARY KEY (`ma_nguyen_vat_lieu`,`ma_phieu_nhap`),
  ADD KEY `FKt9ierohgo7p5i8bjjw6wi3jlh` (`chi_tiet_phieu_nhap_nguyen_vat_lieu_foreign_key`),
  ADD KEY `FK9reox427sx0pctkrnaqc3e9v1` (`chi_tiet_phieu_nhap_phieu_nhap_foreign_key`);

--
-- Indexes for table `hoa_don`
--
ALTER TABLE `hoa_don`
  ADD PRIMARY KEY (`ma_hoa_don`),
  ADD KEY `FKkj71losh6g82surdsuq4hvwbe` (`hoa_don_hoi_vien_foreign_key`),
  ADD KEY `FKdjptraaljhen8wta139df905i` (`hoa_don_khach_hang_foreign_key`),
  ADD KEY `FKtdrxkdky5udarvnab0bpob2om` (`hoa_don_nhan_vien_foreign_key`),
  ADD KEY `FKdrhfku88af7h53ceom786sk7a` (`hoa_don_phieu_goi_mon_foreign_key`);

--
-- Indexes for table `hoi_vien`
--
ALTER TABLE `hoi_vien`
  ADD PRIMARY KEY (`ma_hoi_vien`),
  ADD UNIQUE KEY `UK_csook5xnk32xw1bokdpox0wdb` (`email`);

--
-- Indexes for table `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD PRIMARY KEY (`ma_khach_hang`);

--
-- Indexes for table `lich_hen`
--
ALTER TABLE `lich_hen`
  ADD PRIMARY KEY (`ma_lich_hen`),
  ADD KEY `FKiyed67v05cuosqg6rulawyqk3` (`lich_hen_ban_an_foreign_key`),
  ADD KEY `FKsha0hjrgt2djgsnqp34k6nphc` (`lich_hen_khach_hang_foreign_key`),
  ADD KEY `FKi2rwgpki1rq3n7xpaygrl5qn7` (`lich_hen_nhan_vien_foreign_key`);

--
-- Indexes for table `mon_an`
--
ALTER TABLE `mon_an`
  ADD PRIMARY KEY (`ma_mon_an`),
  ADD KEY `FKmf0kjtq5ugc1ps7e08rjvaq3n` (`mon_an_nhom_mon_an_foreign_key`);

--
-- Indexes for table `nguyen_vat_lieu`
--
ALTER TABLE `nguyen_vat_lieu`
  ADD PRIMARY KEY (`ma_nguyen_vat_lieu`);

--
-- Indexes for table `nhan_vien`
--
ALTER TABLE `nhan_vien`
  ADD PRIMARY KEY (`ma_nhan_vien`),
  ADD UNIQUE KEY `UK_12waxxsiniyddv2lt9ianfh8a` (`email`),
  ADD KEY `FKmsg6em0vf83n4ymqlg65qcu83` (`nhan_vien_tai_khoan_forgein_key`);

--
-- Indexes for table `nha_cung_cap`
--
ALTER TABLE `nha_cung_cap`
  ADD PRIMARY KEY (`ma_nha_cung_cap`),
  ADD UNIQUE KEY `UK_cuf78wlhewgijvkdhpyo1woi5` (`so_dien_thoai`);

--
-- Indexes for table `nhom_mon_an`
--
ALTER TABLE `nhom_mon_an`
  ADD PRIMARY KEY (`ma_nhom`);

--
-- Indexes for table `phieu_goi_mon`
--
ALTER TABLE `phieu_goi_mon`
  ADD PRIMARY KEY (`ma_order`),
  ADD KEY `FKgo2b1hq7d3xn97drkuxi6g6mr` (`phieu_goi_mon_ban_an_foreign_key`),
  ADD KEY `FKfkcxq772ghp2j9ghmgty3d4k` (`phieu_goi_mon_nhan_vien_foreign_key`);

--
-- Indexes for table `phieu_nhap`
--
ALTER TABLE `phieu_nhap`
  ADD PRIMARY KEY (`ma_phieu_nhap`),
  ADD KEY `FKeka7cj4vbauopbxtu5eqk4bwr` (`phieu_nhap_nha_cung_cap_foreign_key`),
  ADD KEY `FKkcney1svi8wveoh66fcemv1i3` (`phieu_nhap_nhan_vien_foreign_key`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`ma_tai_khoan`),
  ADD UNIQUE KEY `UK_pn69efomb5xfas83ph3m73425` (`ten_tai_khoan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ban_an`
--
ALTER TABLE `ban_an`
  MODIFY `ma_ban_an` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hoa_don`
--
ALTER TABLE `hoa_don`
  MODIFY `ma_hoa_don` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hoi_vien`
--
ALTER TABLE `hoi_vien`
  MODIFY `ma_hoi_vien` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khach_hang`
--
ALTER TABLE `khach_hang`
  MODIFY `ma_khach_hang` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `lich_hen`
--
ALTER TABLE `lich_hen`
  MODIFY `ma_lich_hen` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mon_an`
--
ALTER TABLE `mon_an`
  MODIFY `ma_mon_an` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=148;

--
-- AUTO_INCREMENT for table `nguyen_vat_lieu`
--
ALTER TABLE `nguyen_vat_lieu`
  MODIFY `ma_nguyen_vat_lieu` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nhan_vien`
--
ALTER TABLE `nhan_vien`
  MODIFY `ma_nhan_vien` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nha_cung_cap`
--
ALTER TABLE `nha_cung_cap`
  MODIFY `ma_nha_cung_cap` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nhom_mon_an`
--
ALTER TABLE `nhom_mon_an`
  MODIFY `ma_nhom` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `phieu_goi_mon`
--
ALTER TABLE `phieu_goi_mon`
  MODIFY `ma_order` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phieu_nhap`
--
ALTER TABLE `phieu_nhap`
  MODIFY `ma_phieu_nhap` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `ma_tai_khoan` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chi_tiet_goi_mon`
>>>>>>> Stashed changes
--
ALTER TABLE `chi_tiet_goi_mon`
  ADD CONSTRAINT `FKeu5wgck2psgr77w3fy974sgcj` FOREIGN KEY (`chi_tiet_goi_mon_mon_an_foreign_key`) REFERENCES `mon_an` (`ma_mon_an`),
  ADD CONSTRAINT `FKiwdnsh8umb13utgwnh3iolk43` FOREIGN KEY (`chi_tiet_goi_mon_phieu_goi_mon_foreign_key`) REFERENCES `phieu_goi_mon` (`ma_order`);

--
<<<<<<< Updated upstream
-- Các ràng buộc cho bảng `chi_tiet_phieu_nhap`
=======
-- Constraints for table `chi_tiet_phieu_nhap`
>>>>>>> Stashed changes
--
ALTER TABLE `chi_tiet_phieu_nhap`
  ADD CONSTRAINT `FK9reox427sx0pctkrnaqc3e9v1` FOREIGN KEY (`chi_tiet_phieu_nhap_phieu_nhap_foreign_key`) REFERENCES `phieu_nhap` (`ma_phieu_nhap`),
  ADD CONSTRAINT `FKt9ierohgo7p5i8bjjw6wi3jlh` FOREIGN KEY (`chi_tiet_phieu_nhap_nguyen_vat_lieu_foreign_key`) REFERENCES `nguyen_vat_lieu` (`ma_nguyen_vat_lieu`);

--
<<<<<<< Updated upstream
-- Các ràng buộc cho bảng `hoa_don`
=======
-- Constraints for table `hoa_don`
>>>>>>> Stashed changes
--
ALTER TABLE `hoa_don`
  ADD CONSTRAINT `FKdjptraaljhen8wta139df905i` FOREIGN KEY (`hoa_don_khach_hang_foreign_key`) REFERENCES `khach_hang` (`ma_khach_hang`),
  ADD CONSTRAINT `FKdrhfku88af7h53ceom786sk7a` FOREIGN KEY (`hoa_don_phieu_goi_mon_foreign_key`) REFERENCES `phieu_goi_mon` (`ma_order`),
  ADD CONSTRAINT `FKkj71losh6g82surdsuq4hvwbe` FOREIGN KEY (`hoa_don_hoi_vien_foreign_key`) REFERENCES `hoi_vien` (`ma_hoi_vien`),
  ADD CONSTRAINT `FKtdrxkdky5udarvnab0bpob2om` FOREIGN KEY (`hoa_don_nhan_vien_foreign_key`) REFERENCES `nhan_vien` (`ma_nhan_vien`);

--
<<<<<<< Updated upstream
-- Các ràng buộc cho bảng `lich_hen`
=======
-- Constraints for table `lich_hen`
>>>>>>> Stashed changes
--
ALTER TABLE `lich_hen`
  ADD CONSTRAINT `FKi2rwgpki1rq3n7xpaygrl5qn7` FOREIGN KEY (`lich_hen_nhan_vien_foreign_key`) REFERENCES `nhan_vien` (`ma_nhan_vien`),
  ADD CONSTRAINT `FKiyed67v05cuosqg6rulawyqk3` FOREIGN KEY (`lich_hen_ban_an_foreign_key`) REFERENCES `ban_an` (`ma_ban_an`),
  ADD CONSTRAINT `FKsha0hjrgt2djgsnqp34k6nphc` FOREIGN KEY (`lich_hen_khach_hang_foreign_key`) REFERENCES `khach_hang` (`ma_khach_hang`);

--
<<<<<<< Updated upstream
-- Các ràng buộc cho bảng `mon_an`
=======
-- Constraints for table `mon_an`
>>>>>>> Stashed changes
--
ALTER TABLE `mon_an`
  ADD CONSTRAINT `FKmf0kjtq5ugc1ps7e08rjvaq3n` FOREIGN KEY (`mon_an_nhom_mon_an_foreign_key`) REFERENCES `nhom_mon_an` (`ma_nhom`);

--
<<<<<<< Updated upstream
-- Các ràng buộc cho bảng `nhan_vien`
--
ALTER TABLE `nhan_vien`
  ADD CONSTRAINT `FK6dvy8lexgwts2iak6gf5snksp` FOREIGN KEY (`nhan_vien_tai_khoan_forgein_key`) REFERENCES `tai_khoan` (`ma_tai_khoan`);

--
-- Các ràng buộc cho bảng `phieu_goi_mon`
=======
-- Constraints for table `nhan_vien`
--
ALTER TABLE `nhan_vien`
  ADD CONSTRAINT `FKmsg6em0vf83n4ymqlg65qcu83` FOREIGN KEY (`nhan_vien_tai_khoan_forgein_key`) REFERENCES `taikhoan` (`ma_tai_khoan`);

--
-- Constraints for table `phieu_goi_mon`
>>>>>>> Stashed changes
--
ALTER TABLE `phieu_goi_mon`
  ADD CONSTRAINT `FKfkcxq772ghp2j9ghmgty3d4k` FOREIGN KEY (`phieu_goi_mon_nhan_vien_foreign_key`) REFERENCES `nhan_vien` (`ma_nhan_vien`),
  ADD CONSTRAINT `FKgo2b1hq7d3xn97drkuxi6g6mr` FOREIGN KEY (`phieu_goi_mon_ban_an_foreign_key`) REFERENCES `ban_an` (`ma_ban_an`);

--
<<<<<<< Updated upstream
-- Các ràng buộc cho bảng `phieu_nhap`
=======
-- Constraints for table `phieu_nhap`
>>>>>>> Stashed changes
--
ALTER TABLE `phieu_nhap`
  ADD CONSTRAINT `FKeka7cj4vbauopbxtu5eqk4bwr` FOREIGN KEY (`phieu_nhap_nha_cung_cap_foreign_key`) REFERENCES `nha_cung_cap` (`ma_nha_cung_cap`),
  ADD CONSTRAINT `FKkcney1svi8wveoh66fcemv1i3` FOREIGN KEY (`phieu_nhap_nhan_vien_foreign_key`) REFERENCES `nhan_vien` (`ma_nhan_vien`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
