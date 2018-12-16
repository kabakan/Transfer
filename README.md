# Transfer
#http://localhost:8587/swagger-ui.html

#Для тестирования создание счетов и переводы RavolutTransferAccount

#1) Операция со счетаами 

#1.1. Счет отправителя

curl -X POST \
  http://localhost:8587/api/accounts/add \
  -H 'Content-Type: application/json' \
  -d '{"clientName":"Alex Li",
 "accCode":"ACC5577786724",
 "currCode":"USD",
 "summ":1000.0
}'

#1.2. Cчет получателя

curl -X POST \
  http://localhost:8587/api/accounts/add \
  -H 'Content-Type: application/json' \
  -d '{"clientName":"Danil Kim",
 "accCode":"ACC5577417899",
 "currCode":"USD",
 "summ":100.0
}'

#1.3. Список счетов

curl -X GET http://localhost:8587/api/accounts/getAll

#1.4. Изменить счет

curl -X POST \
  http://localhost:8587/api/accounts/update \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 2,
  "clientName": "Danil Kim",
  "accCode": "ACC5577417899",
  "currCode": "USD",
  "summ": 401
}'

#1.6. Удалить счет

curl -X POST \
  'http://localhost:8587/api/accounts/delete?id=1' \
  -H 'Content-Type: application/json'


#1.7. Проверить счет

curl -X GET 'http://localhost:8587/api/accounts/get?accCode=ACC5577417899'

#1.8 Список валют

curl -X GET http://localhost:8587/api/accounts/getCurrency

#1.9 Генератор код счета

curl -X GET http://localhost:8587/api/accounts/generate  

#2) Операция с переводами

#2.1. Создать перевод

curl -X POST \
  http://localhost:8587/api/transfer/add \
  -H 'Content-Type: application/json' \
  -d '{
"fromAccCode":"ACC5577786724",
"toAccCode":"ACC5577417899",
"currCode":"USD",
"summ":300
}'

#2.2. Список переводов

curl -X GET http://localhost:8587/api/transfer/getAll

#2.2. Выполнить перевод

curl -X GET http://localhost:8587/api/transfer/send

#2.3. Список выполненных переоводов в архиве

curl -X GET http://localhost:8587/api/archTransfer/getAll
  
