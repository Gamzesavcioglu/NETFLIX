Proje Amacı:

Program çalıştırıldığında LoginController ara yüzü açılır. Burada kullanıcının e-mail ve şifre bilgilerini yazıp Login butonuyla giriş yapması istenir. Eğer bilgileri veri tabanındaki kullanici tablosunda bulunmazsa kayıt olması istenmektedir. Kullanıcının kayıt işlemi için kayıt ol butonuna basması gerekmektedir. Eğer kullanıcı kayıt ol butonuna basarsa KullaniciFormController ara yüzüne geçiş yapılmaktadır.

KullaniciFormController ara yüzünde oluşturulan TextField’larda kullanıcıdan sırasıyla adı, e-mail adresi, şifre, doğum tarihi ve ComboBox içine kaydedilen türler arasından sevdiği üç tane tür seçmesi istenmektedir ve Kayıt Ol butonuna basması gerekmektedir. Bu işlem ile kullanıcının girdiği bilgiler veri tabanından kullanici tablosuna kaydedilmiştir.

Ardından ProgramFormController ara yüzüne geçilir. Bu ara yüzde kullanıcının seçtiği türlere göre veri tabanında programtur tablosunda tür bilgileri aratılarak her seçtiği tür için bulunan ikişer tane program ve veri tabanına kayıtlı program tablosunda bulunan tüm programların bilgisi gösterilmektedir.

Kullanıcı veri tabanına kayıtlı program bilgilerini içeren tabloda istediği programı seçebilir. Buna ek olarak program adı veya türüne göre arama yapabilmektedir. Bulunan içerikler ekranda gösterilmektedir. Kullanıcı program izlemek istiyor ise her program için oluşturulmuş IZLE butonuna basması gerekmektedir. IZLE butonuna bastıktan sonra izlenen programın izlenmeye başlandığı kabul edilip IzleFormController ara yüzüne geçiş yapılmaktadır. 

Bu ara yüzde kullanıcının izlediği programın adını gösteren TextField, BİTİR, DEVAM ve GERİ butonları bulunmaktadır. Eğer kullanıcı izlediği programı sonlandırmak istiyorsa BİTİR butonuna basmalıdır. Öncelikle İZLE butonuna bastıktan sonra başlatılan programın izlenme süresi sonlandırılmaktadır. Ardından kullanıcının izlediği programa puan vermesi gerekmektedir. Kullanıcı puan verdikten sonra veri tabanında bulunan kullaniciprogram tablosuna kullanıcın e-mail adresi, izlediği programın adı, izlediği tarih, eğer dizi izliyorsa kaldığı bölüm ve kullanıcıdan alınan puan bilgisi tabloya kaydedilir.

Eğer kullanıcı başka bir program izlemek istiyorsa IzleFormController ara yüzünde bulunan GERİ butonuna basıp program bilgilerini içeren ProgramFormController ara yüzüne geçiş yapabilmektedir. Buradan tekrar kullanıcı izlemek istediği programı seçerek IZLE butonu ile IzleForm Controller ara yüzüne geçer ve aynı şekilde isterleri yerine getirmesi gerekmektedir.

Böylece veri tabanında kullanıcı için izlediği programlar kaydedilmiş olur.
 
Eğer kullanıcı en son dizi izlemiş ve programdan çıkış yapmışsa ve izlediği dizinin bölümleri tamamlanmamışsa programa tekrar giriş yaptığında için IzleFormController ara yüzü açılır ve diziye kaldığı yerden DEVAM butonuna basarak izlemeye devam edebilmektedir. Kullanıcının tekrar BİTİR butonuna basmasıyla veri tabanında buluna kullaniciprogram tablosunda izleme süresi, izleme tarihi ve kaldığı bölüm bilgileri güncellenir. Ayrıca programa tekrar puan vermesi istenmez.






Projenin Çalıştırılması:


Projemiz Java 8.02310.11 sürümü ile yapılmıştır. Proje çalıştırılmasında bu sürüm için proje import edilecek ise içinde referenced libraries olarak mysql connector j bulunmaktadır. Ancak classlar kopyala yapıştır yapılacak ise mysql java connector j kütüphane
olarak eklenmelidir.(mysqlconnector J  Veritabanı klasörü içinde yer almaktadır.Oradan alınıp eklenebilir.)

Daha sonra proje yapımında MySQL Workbench 8.0 sürümü kullanılmış ve datalar proje zip'e Veritabanı isimli klasöriçine  Dump20200531 isimli klasör olarak  konulmuştur. Bu datalar Workbench 8.0 e import edilmelidir.(import işleminde Workbench'te proje isimli schema oluşturulmalı ve belirtilen dosya yolu içine Dump20200531 klasörü konulmalı ve import işlemleri Workbench'ten yapılmalıdır.)

Bu işlemler ardından java projesinin import edildiği IDE de  com.IsteMysql.Util package altında bulunan VeritabaniUtil classına gelinmeli ve bu classta bulunan aşağıdaki satıra veri tabanına bağlanmak için size ait şifre ile giriş yapabildiğiniz 

Workbench 8.0 giriş şifrenizi ve kullanıcı adınızı  girmelisiniz.

 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey","root","123");

Şifreyi ise "123" yazan kısımda tırnak içerisine yazmanız gerekmektedir.

Kullanıcı adınızı "root" kısmında tırnak içerisine yazmalısınız.

Ayrıca localhost:3306 kısmınında kullandığınız Workbench sürümünde farklılık varsa değiştirilmesi gerekmektedir.

Ayrıca Workbench 8.0 a import ettiğiniz data adı aşağıda proje yazan kısım ile aynı olmalıdır.(datayı import ettiğinizde Workbench'e adı proje ise isim aşağıda aynı kalmalı ama farklı bir isimle import ederseniz aşağıdaki satırda proje yerine dataya verdiğiniz isim ile değiştirilmelidir.)

 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey","root","123");