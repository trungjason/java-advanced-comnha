namespace ComNha.Models
{
    public class LoginModel
    {
        public static readonly string ROLE_WAITER = "Phục Vụ";
        public static readonly string ROLE_RECEPTIONIST = "Lễ Tân";
        public static readonly string ROLE_CASHIER = "Thu Ngân";
        public static readonly string ROLE_INVENTORY_MANAGER = "Quản Lý Kho";
        public static readonly string ROLE_MANAGER = "Quản Lý Nhà Hàng";

        public static readonly string[] ROLE = { ROLE_WAITER, ROLE_RECEPTIONIST, ROLE_CASHIER, ROLE_INVENTORY_MANAGER, ROLE_MANAGER };

        public string staffID { get; set; }

        public string role { get; set; }
    }
}
