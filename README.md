# B25DCCC104_NguyenHoangHai_BaiTapHocPhi

## Cấu trúc Repository & Cách xem bài trên GitHub

**Chuyển nhánh trên giao diện web GitHub:**
1. Truy cập trang chính của repository: https://github.com/hoangem01/B25DCCC104_NguyenHoangHai_BaiTapHocPhi
2. Bấm vào nút chọn nhánh **`main`** ở góc trên bên trái danh sách tệp.
3. Chọn nhánh tương ứng (`cau-1`, `cau-2`, `cau-3` hoặc `cau-4`) và mở file `Main.java`.

## Hướng dẫn chạy chương trình

### Yêu cầu môi trường
* Đã cài đặt **Java Development Kit (JDK)** (phiên bản 8 trở lên).
* Hỗ trợ bảng mã **UTF-8** khi biên dịch và chạy trên Terminal/Command Prompt.

```bash
# 1. Clone repository về máy
git clone [https://github.com/hoangem01/B25DCCC104_NguyenHoangHai_BaiTapHocPhi.git](https://github.com/hoangem01/B25DCCC104_NguyenHoangHai_BaiTapHocPhi.git)
cd B25DCCC104_NguyenHoangHai_BaiTapHocPhi

# 2. Chuyển sang nhánh của câu muốn kiểm tra (ví dụ: cau-1)
git checkout cau-1

# 3. Biên dịch và chạy chương trình
javac -encoding UTF-8 Main.java
java Main
```
*(Để kiểm tra các câu tiếp theo, chỉ cần chạy `git checkout cau-2`, `git checkout cau-3` hoặc `git checkout cau-4` rồi biên dịch và chạy lại tương tự).*

