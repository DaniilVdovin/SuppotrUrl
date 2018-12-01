# Parse-java-Android

Этот код разработан для разбора таблиц с любого сайта в массив
В коде я постараюсь подробно все описать, что за что отвечает

# Model/SupportUrl
.java Class для Проверки подключения к интернету и для создание ссылок с аргументами из строк и массива.

Для подключение скачиваем файл и помещаем в папку c проектом, в основном классе указываем путь к нему.
```
import static [Ваш путь].SupportUrl.*;
```
Пользуемся
```
CreateUrlArg.OneStringyArg("file:///android_asset/index.html","D=O",[любое количество аргументов])
```
вернет ссылку с аргументами 
```
isNetwork(this)
```
вернет true или false
```
String array[][] ={{"S","Y"},
                   {"K","A"}} 
CreateUrlArg.AllArrayArg("file:///android_asset/index.html", array) 
```
вернет ссылку с аргументами 
