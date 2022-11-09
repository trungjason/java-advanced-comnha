using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace ComNha_API.Models
{
    public partial class quanlynhahangContext : DbContext
    {
        public quanlynhahangContext()
        {
        }

        public quanlynhahangContext(DbContextOptions<quanlynhahangContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Banan> Banans { get; set; } = null!;
        public virtual DbSet<Chitietgoimon> Chitietgoimons { get; set; } = null!;
        public virtual DbSet<Chitietphieunhap> Chitietphieunhaps { get; set; } = null!;
        public virtual DbSet<Hoadon> Hoadons { get; set; } = null!;
        public virtual DbSet<Hoivien> Hoiviens { get; set; } = null!;
        public virtual DbSet<Khachhang> Khachhangs { get; set; } = null!;
        public virtual DbSet<Lichhen> Lichhens { get; set; } = null!;
        public virtual DbSet<Monan> Monans { get; set; } = null!;
        public virtual DbSet<Nguyenvatlieu> Nguyenvatlieus { get; set; } = null!;
        public virtual DbSet<Nhacungcap> Nhacungcaps { get; set; } = null!;
        public virtual DbSet<Nhanvien> Nhanviens { get; set; } = null!;
        public virtual DbSet<Nhommonan> Nhommonans { get; set; } = null!;
        public virtual DbSet<Phieugoimon> Phieugoimons { get; set; } = null!;
        public virtual DbSet<Phieunhap> Phieunhaps { get; set; } = null!;
        public virtual DbSet<Taikhoan> Taikhoans { get; set; } = null!;

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see http://go.microsoft.com/fwlink/?LinkId=723263.
                optionsBuilder.UseSqlServer("Server=DESKTOP-IJV47EK;Database=quanlynhahang;Trusted_Connection=True;");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Banan>(entity =>
            {
                entity.HasKey(e => e.MaBanAn)
                    .HasName("PK__banan__E2673DF2C635F6C2");

                entity.ToTable("banan");

                entity.Property(e => e.MaBanAn).HasMaxLength(10);

                entity.Property(e => e.GhiChu).HasMaxLength(50);

                entity.Property(e => e.TinhTrang).HasMaxLength(20);
            });

            modelBuilder.Entity<Chitietgoimon>(entity =>
            {
                entity.HasKey(e => new { e.MaMonAn, e.MaOrder })
                    .HasName("PK__chitietg__D4124FCAE51ED8A7");

                entity.ToTable("chitietgoimon");

                entity.Property(e => e.MaMonAn).HasMaxLength(10);

                entity.Property(e => e.MaOrder).HasMaxLength(10);

                entity.HasOne(d => d.MaMonAnNavigation)
                    .WithMany(p => p.Chitietgoimons)
                    .HasForeignKey(d => d.MaMonAn)
                    .HasConstraintName("FK_ChiTietGoiMon_MonAn");

                entity.HasOne(d => d.MaOrderNavigation)
                    .WithMany(p => p.Chitietgoimons)
                    .HasForeignKey(d => d.MaOrder)
                    .HasConstraintName("FK_ChiTietGoiMon_PhieuGoiMon");
            });

            modelBuilder.Entity<Chitietphieunhap>(entity =>
            {
                entity.HasKey(e => new { e.MaNguyenVatLieu, e.MaPhieuNhap })
                    .HasName("PK__chitietp__829F1A2E5B545444");

                entity.ToTable("chitietphieunhap");

                entity.Property(e => e.MaNguyenVatLieu).HasMaxLength(10);

                entity.Property(e => e.MaPhieuNhap).HasMaxLength(10);

                entity.HasOne(d => d.MaNguyenVatLieuNavigation)
                    .WithMany(p => p.Chitietphieunhaps)
                    .HasForeignKey(d => d.MaNguyenVatLieu)
                    .HasConstraintName("FK_ChiTietPhieuNhap_NguyenVatLieu");

                entity.HasOne(d => d.MaPhieuNhapNavigation)
                    .WithMany(p => p.Chitietphieunhaps)
                    .HasForeignKey(d => d.MaPhieuNhap)
                    .HasConstraintName("FK_ChiTietPhieuNhap_PhieuNhap");
            });

            modelBuilder.Entity<Hoadon>(entity =>
            {
                entity.HasKey(e => e.MaHoaDon)
                    .HasName("PK__hoadon__835ED13B08C4C875");

                entity.ToTable("hoadon");

                entity.Property(e => e.MaHoaDon).HasMaxLength(10);

                entity.Property(e => e.ChietKhau).HasDefaultValueSql("((0))");

                entity.Property(e => e.MaHoiVien).HasMaxLength(20);

                entity.Property(e => e.MaNhanVien).HasMaxLength(10);

                entity.Property(e => e.MaOrder).HasMaxLength(10);

                entity.Property(e => e.ThoiGianThanhToan)
                    .HasColumnType("datetime")
                    .HasDefaultValueSql("(getdate())");

                entity.Property(e => e.TinhTrang).HasDefaultValueSql("('0')");

                entity.Property(e => e.TongTien).HasDefaultValueSql("((0))");
            });

            modelBuilder.Entity<Hoivien>(entity =>
            {
                entity.HasKey(e => e.SoDienThoai)
                    .HasName("PK__hoivien__0389B7BCE047CFF3");

                entity.ToTable("hoivien");

                entity.Property(e => e.SoDienThoai).HasMaxLength(20);

                entity.Property(e => e.DiaChi).HasMaxLength(100);

                entity.Property(e => e.Email).HasMaxLength(100);

                entity.Property(e => e.MatKhau).HasMaxLength(100);

                entity.Property(e => e.QuyenLoi).HasMaxLength(100);

                entity.Property(e => e.TenHoiVien).HasMaxLength(50);
            });

            modelBuilder.Entity<Khachhang>(entity =>
            {
                entity.HasKey(e => e.MaKhachHang)
                    .HasName("PK__khachhan__88D2F0E5731BA7CC");

                entity.ToTable("khachhang");

                entity.Property(e => e.DiaChi).HasMaxLength(50);

                entity.Property(e => e.Email).HasMaxLength(50);

                entity.Property(e => e.SoDienThoai).HasMaxLength(20);

                entity.Property(e => e.TenKhachHang).HasMaxLength(50);
            });

            modelBuilder.Entity<Lichhen>(entity =>
            {
                entity.HasKey(e => e.MaLichHen)
                    .HasName("PK__lichhen__150F264F874E9D8A");

                entity.ToTable("lichhen");

                entity.Property(e => e.MaLichHen).HasMaxLength(10);

                entity.Property(e => e.MaBanAn).HasMaxLength(10);

                entity.Property(e => e.MaNhanVien).HasMaxLength(10);

                entity.Property(e => e.NgayHen).HasColumnType("date");

                entity.Property(e => e.NhuCau).HasMaxLength(600);

                entity.Property(e => e.TinhTrang).HasDefaultValueSql("('0')");
            });

            modelBuilder.Entity<Monan>(entity =>
            {
                entity.HasKey(e => e.MaMonAn)
                    .HasName("PK__monan__B117162574C403D7");

                entity.ToTable("monan");

                entity.Property(e => e.MaMonAn).HasMaxLength(10);

                entity.Property(e => e.DonVi).HasMaxLength(50);

                entity.Property(e => e.HinhAnh).HasMaxLength(100);

                entity.Property(e => e.MaNhom).HasMaxLength(10);

                entity.Property(e => e.MoTa).HasMaxLength(500);

                entity.Property(e => e.MoTaNgan).HasMaxLength(100);

                entity.Property(e => e.TenMonAn).HasMaxLength(50);
            });

            modelBuilder.Entity<Nguyenvatlieu>(entity =>
            {
                entity.HasKey(e => e.MaNguyenVatLieu)
                    .HasName("PK__nguyenva__23D814DDFC33CB71");

                entity.ToTable("nguyenvatlieu");

                entity.Property(e => e.MaNguyenVatLieu).HasMaxLength(10);

                entity.Property(e => e.DonVi).HasMaxLength(50);

                entity.Property(e => e.TenNguyenVatLieu).HasMaxLength(50);

                entity.Property(e => e.TinhTrang).HasMaxLength(50);
            });

            modelBuilder.Entity<Nhacungcap>(entity =>
            {
                entity.HasKey(e => e.MaNhaCungCap)
                    .HasName("PK__nhacungc__53DA920522C7DCCA");

                entity.ToTable("nhacungcap");

                entity.Property(e => e.MaNhaCungCap).HasMaxLength(10);

                entity.Property(e => e.DiaChi).HasMaxLength(100);

                entity.Property(e => e.MoTaNhaCungCap).HasMaxLength(50);

                entity.Property(e => e.SoDienThoai).HasMaxLength(20);

                entity.Property(e => e.TenNhaCungCap).HasMaxLength(50);
            });

            modelBuilder.Entity<Nhanvien>(entity =>
            {
                entity.HasKey(e => e.MaNhanVien)
                    .HasName("PK__nhanvien__77B2CA47A81E080E");

                entity.ToTable("nhanvien");

                entity.Property(e => e.MaNhanVien).HasMaxLength(10);

                entity.Property(e => e.ChucVu).HasMaxLength(50);

                entity.Property(e => e.DiaChi).HasMaxLength(100);

                entity.Property(e => e.Email).HasMaxLength(100);

                entity.Property(e => e.SoDienThoai).HasMaxLength(20);

                entity.Property(e => e.TenNhanVien).HasMaxLength(100);
            });

            modelBuilder.Entity<Nhommonan>(entity =>
            {
                entity.HasKey(e => e.MaNhom)
                    .HasName("PK__nhommona__234F91CD54958C4E");

                entity.ToTable("nhommonan");

                entity.Property(e => e.MaNhom).HasMaxLength(10);

                entity.Property(e => e.TenNhom).HasMaxLength(50);
            });

            modelBuilder.Entity<Phieugoimon>(entity =>
            {
                entity.HasKey(e => e.MaOrder)
                    .HasName("PK__phieugoi__50559EF74D1AD884");

                entity.ToTable("phieugoimon");

                entity.Property(e => e.MaOrder).HasMaxLength(10);

                entity.Property(e => e.GhiChuMonAn).HasMaxLength(600);

                entity.Property(e => e.MaBanAn).HasMaxLength(10);

                entity.Property(e => e.MaNhanVien).HasMaxLength(10);
            });

            modelBuilder.Entity<Phieunhap>(entity =>
            {
                entity.HasKey(e => e.MaPhieuNhap)
                    .HasName("PK__phieunha__1470EF3BABAE3AC6");

                entity.ToTable("phieunhap");

                entity.Property(e => e.MaPhieuNhap).HasMaxLength(10);

                entity.Property(e => e.GhiChu).HasMaxLength(100);

                entity.Property(e => e.MaNhaCungCap).HasMaxLength(10);

                entity.Property(e => e.MaNhanVien).HasMaxLength(10);

                entity.Property(e => e.NgayNhapHang).HasColumnType("datetime");
            });

            modelBuilder.Entity<Taikhoan>(entity =>
            {
                entity.HasKey(e => e.TenTaiKhoan)
                    .HasName("PK__taikhoan__B106EAF9DF5FDDFC");

                entity.ToTable("taikhoan");

                entity.Property(e => e.TenTaiKhoan).HasMaxLength(50);

                entity.Property(e => e.MaNhanVien).HasMaxLength(10);

                entity.Property(e => e.MatKhau).HasMaxLength(255);
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
