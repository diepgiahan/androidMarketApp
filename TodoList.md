# todolist and note

--- 2018-07-22 @Han ---

Restrict lại cái range của số lượng mỗi món
. số min = 0:
lúc đầu số đang là 1, giảm xuống nó vẫn có thể xuống âm, nhảy nấc -2
xong tăng từ số âm lên lại thì xuống không còn âm nữa..
. số max = số lượng còn lại của món đó

Cart list cho thêm nút remove item ~ không mua nữa

. resizing icons..
cái ngay dưới flash sale chắc nên là 1 class khác thay vì dùng chung với product ~ set icon nhỏ lại, bỏ khung prices

Tiny: click SliderDots = click the flash picture


I will add s.t to handle the softInputKeyboard ~ hide it when not use..

CHANGES:
. strike through text:
holder.oldprice.setPaintFlags(holder.oldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
. activity main xml:
add FrameLayout to SliderDots
. Toggle search button ~ hiding part not working properly
. FrameLayout for suggest search
