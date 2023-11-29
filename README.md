# Từ điển Anh - Việt
Bài tập trong môn Lập trình hướng đối tượng UET - VNU

## Sinh viên
- [Dương Minh Hoàng] - 22021218
- [Triệu Minh Nhật] - 22021214
## Các chức năng chính
### Tra từ
- Hệ thống từ điển sẽ kết nối với 1 cơ sở dữ liệu để lấy các thuộc tính như **nghĩa**, **phiên âm**, **từ loại**,... của từ mà người dùng đang tìm kiếm
- Người dùng có khả năng tra cứu cả nghĩa trong Tiếng Anh và Tiếng Việt của từ vựng, linh hoạt theo nhu cầu và mong muốn cá nhân.
### Phát âm
- Nguồn phát âm được lấy từ [Cambridge Dictionary](https://dictionary.cambridge.org/dictionary/english) 
- Mỗi từ Tiếng Anh đều có thể phát âm được theo Anh - Anh hoặc Anh - Mỹ
### Dịch văn bản
- Nguồn dịch văn bản được lấy từ [Google Translate và TTS API](https://stackoverflow.com/questions/8147284/how-to-use-google-translate-api-in-my-java-application)
- Hệ thống có cả năng dịch Tiếng Anh sang Tiếng Việt, mỗi đoạn văn bản đều có tích hợp âm thanh
### Thêm, sửa, xóa từ vựng
- Nếu cần thiết, người dùng có thể thêm từ mới vào từ điển hoặc có thể sửa định nghĩa, xóa từ vựng ra khỏi từ điển
- Khi từ vựng bị xóa khỏi Từ điển, người dùng vẫn có thể khôi phục được trong phần **Restore**
### Các chức năng khác
- Ngoài những chức năng chính, Từ điển còn hỗ trợ các tính năng như **lưu** lại lịch sử tìm kiếm, **thêm** từ vào mục yêu thích, **khôi phục** lại từ đã bị xóa...
## Các công nghệ và tài nguyên
- Cơ Sở Dữ Liệu Tối Ưu: Tích hợp cơ sở dữ liệu để cải thiện quá trình tìm kiếm, giúp người dùng nhanh chóng và chính xác truy cập thông tin từ vựng.
- Nguồn Từ Vựng Phong Phú: Hệ thống cung cấp hơn 140.000 từ vựng Tiếng Anh và Tiếng Việt, đảm bảo nguồn tài nguyên đa dạng và đầy đủ để đáp ứng mọi nhu cầu của người sử dụng.
- Nguồn Tài Liệu Uy Tín: Sử dụng các nguồn tài liệu uy tín được thế giới sử dụng rộng rãi để đảm bảo chất lượng và độ chính xác của thông tin từ vựng cung cấp.
## Giải trí
- Ngoài phần từ điển, hệ thống còn có game học từ vựng **HangMan** và **Multiple Choices** 
### Hướng dẫn chơi game:
- Người chơi có 6 ô đất để trồng cây.
- Người chơi có thể vào cửa hàng để mua **hạt giống**.
- Có 5 loại cây có thể trồng **Củ cải, Dưa hấu, Bí ngô, Khế, Berry** có giá trị khác nhau.
- Trồng cây thì cũng phải tưới cây.
- Tưới xong thì có thể sang ngày mới, sang ngày mới mà cây không được tưới sẽ bị héo.
- Sang ngày mới thì phải chơi trò **HangMan**, nếu thua sẽ có cây héo.
- Mỗi cây có thời gian trồng riêng, giá trị càng cao thì lớn càng lâu.
- Cây lớn có thể thu hoạch, khi đó sẽ phải chơi **Multiple Choices**, trả lời đúng càng nhiều thì càng nhận được nhiều sản phẩm.
- Sản phẩm thu hoạch sẽ vào trong kho.
- Có thể vào của hàng để bán sản phẩm, nhấn + hoặc - để thay đổi số lượng hàng, số lượng hàng có giá trị âm là đang bán, nhấn **Mua** để bán, số lượng dương thì là đang mua, nhấn **Mua** để mua.
