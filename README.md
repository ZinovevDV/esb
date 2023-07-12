# databus
## corporate data bus

### map

```mermaid
stateDiagram
	in_adapter_1..n --> producer: message
	producer --> in_adapter_1..n: message_id
	producer --> register: check route / adapter
	producer --> minio: attach
	register --> producer: full route if done
	register --> register_db
	register_db --> register
	producer --> message_broker_message_to_messager: message
	message_broker_message_to_messager --> messager
	messager --> messager_db
	messager --> message_broker_message_to_router: message + routes
	message_broker_message_to_router --> router
	router --> router_db
	router --> message_broker_route_task_to_processor
	message_broker_route_task_to_processor --> processor_1..n
	processor_1..n --> message_broker_task_ok_to_router
	processor_1..n --> message_broker_task_fail_to_router
	message_broker_task_ok_to_router --> router
	message_broker_task_fail_to_router --> router
	router --> message_broker_route_complete_consumer
	message_broker_route_complete_consumer --> consumer
	consumer --> out_adapter_1..n
	consumer --> router: delivery receipt
	router --> message_broker_receipt_to_delivery: delivery receipt
	message_broker_receipt_to_delivery --> delivery
	delivery --> in_adapter_1..n 
```

### in_adapter_1..n
Входящие адаптеры- системы, "создающие" сообщения.
Должны знать маршрут, по которому они отправляют сообщение, а также семантику сообщения
Пример:
***in_adapter_from_folder*** - забирает все файлы из указанного каталога

### register
Сервис, позволяющий валидировать, может ли указанная система с указанным адаптером оптравлять сообщения по выбранному маршруту.
Также отвечает за регистрацию систем/адаптеров и маршрутов.



### маршрут
Список процессоров для обработки сообщения. Может быть пустым.
А также список получателей сообщения.



