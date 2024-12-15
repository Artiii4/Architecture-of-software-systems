# Architecture-of-software-systems
<pre>
"Описание системы:
ИБ — бесконечный источник;
ИЗ2 — равномерный закон распределения;
ПЗ1— экспоненциальный закон распределения времени обслуживания 
Д1033—постановка заявки на свободное место
Д10О4 — дисциплина отказа: последняя заявка, поступившая в буфер
Д2П2 —выбор прибора по кольцу
Д2Б3 —выбор заявки из буфера по кольцу
ОР1—отображение результатов сводной таблицей результатов
ОД2 —отображение динамики функционирования модели(формализованная схема модели, текущее состояние)

После запуска программа ожидает ввод данных в консоль. 
Если пользователь введет букву "E" (сокращение от enter), то программа запросит: вместимость буфера, количество мастеров (приборов), 
количество заявок, которые нужно сгенерировать. Иначе программа будет работать на изначальных данных. 
Далее программа предложит выбрать режим работы, всего есть 2:"S" (сокращение от step-by-step), "A" (сокращение от automatic). 
Для выбора нужно ввести либо "S", чтобы выбрать пошаговый режим, либо "A" для выполнения моделирования в автоматическом режиме.

Описание пошагового режима:
Пользовать нажимает любые клавиши для выполнения каждого шага, при этом в консоли будет выводиться информация о:
-времени;
-генерации новых заявок;
-состоянии буфера;
-начале выполнения заявок мастерами (если они свободны) инеобходимое время для выполнения заказа;
-освобождении мастеров (если они выполнили заказы).
Пример вывода:
Time: 0

The request buffer is empty.
Generated Request ID: 1, Description: Board burned out
Technician David Anderson is processing request 1  service time 1.2606368677703026
Assigned Request 1 with discriptionBoard burned out to Technician David Anderson
The request buffer is empty.
Generated Request ID: 2, Description: Hard drive issue
Technician Tom Miller is processing request 2  service time 0.44689456925887033
Assigned Request 2 with discriptionHard drive issue to Technician Tom Miller
The request buffer is empty.
Generated Request ID: 3, Description: Board burned out
Technician Denis Starov is processing request 3  service time 3.1236723289914945
Assigned Request 3 with discriptionBoard burned out to Technician Denis Starov
The request buffer is empty.
Generated Request ID: 4, Description: Replace hardware
Technician Tom Brown is processing request 4  service time 0.5233351293547819
Assigned Request 4 with discriptionReplace hardware to Technician Tom Brown
The request buffer is empty.
Generated Request ID: 5, Description: Replace hardware
Technician Thomsa Anderson is processing request 5  service time 1.7947366630071515
Assigned Request 5 with discriptionReplace hardware to Technician Thomsa Anderson
The request buffer is empty.
Generated Request ID: 6, Description: Install software
Technician Mike Green is processing request 6  service time 1.6835965384516283
Assigned Request 6 with discriptionInstall software to Technician Mike Green
The request buffer is empty.
Generated Request ID: 7, Description: Network issue
Technician Thomsa Green is processing request 7  service time 1.7056116424258192
Assigned Request 7 with discriptionNetwork issue to Technician Thomsa Green
The request buffer is empty.
Generated Request ID: 8, Description: Replace hardware
Technician Thomsa Kelly is processing request 8  service time 1.5552405493285653
Assigned Request 8 with discriptionReplace hardware to Technician Thomsa Kelly
The request buffer is empty.
Generated Request ID: 9, Description: Fix printer
Technician Denis Miller is processing request 9  service time 0.4417131828387626
Assigned Request 9 with discriptionFix printer to Technician Denis Miller
The request buffer is empty.
Generated Request ID: 10, Description: Replace hardware
Technician John Kelly is processing request 10  service time 1.794281824882973
Assigned Request 10 with discriptionReplace hardware to Technician John Kelly
The request buffer is empty.
Technician Tom Miller is free for now
Technician Tom Brown is free for now
Technician Denis Miller is free for now
The request buffer is empty.

Описание автоматического режима:
Пользователю не требуется предпринимать никаких действий. 
По завершении работы программы будет выведена информация о работе модели.
Пример вывода:
Summary Statistics:
| Requests   | Generators | Technicians     | Buffer size     | Time in system  | Rejection probability     | Average workload     | Uniform distribution law:  Left boundary   | Right boundary  |
===================================================================================================================================================================================================
| 4200       | 25         | 20              | 10              | 293             | 6.50                      | 93.33                |                            0               | 2               |
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Technician Workloads:
| ID   | Name            | Busy Time    | Load (%) |
====================================================
| 1    | Kevin Sergeev   | 293.0        | 100.0    |
| 2    | Denis Sergeev   | 293.0        | 100.0    |
| 3    | Ivan Miller     | 293.0        | 100.0    |
| 4    | Ivan Starov     | 293.0        | 100.0    |
| 5    | David Brown     | 293.0        | 100.0    |
| 6    | David Kelly     | 293.0        | 100.0    |
| 7    | Bob Miller      | 293.0        | 100.0    |
| 8    | Kevin Miller    | 292.0        | 99.7     |
| 9    | Bob Starov      | 293.0        | 100.0    |
| 10   | Andrew Brown    | 292.0        | 99.7     |
| 11   | Mike Anderson   | 293.0        | 100.0    |
| 12   | David Kelly     | 290.0        | 99.0     |
| 13   | Tom Brown       | 288.0        | 98.3     |
| 14   | Mike Smith      | 279.0        | 95.2     |
| 15   | John Brown      | 268.0        | 91.5     |
| 16   | Bob Brown       | 257.0        | 87.7     |
| 17   | Tom Anderson    | 228.0        | 77.8     |
| 18   | Kevin Anderson  | 189.0        | 64.5     |
| 19   | Bob Miller      | 157.0        | 53.6     |
| 20   | Ivan Kelly      | 292.0        | 99.7     |
----------------------------------------------------

Request Generators Info:
| ID   | Created Requests     | Removed Requests     | Remaining Reque | Rejection Proba      |
===============================================================================================
| 0    | 169                  | 0                    | 169             | 0.00                 |
| 1    | 181                  | 0                    | 181             | 0.00                 |
| 2    | 170                  | 0                    | 170             | 0.00                 |
| 3    | 166                  | 0                    | 166             | 0.00                 |
| 4    | 170                  | 0                    | 170             | 0.00                 |
| 5    | 171                  | 0                    | 171             | 0.00                 |
| 6    | 169                  | 0                    | 169             | 0.00                 |
| 7    | 180                  | 0                    | 180             | 0.00                 |
| 8    | 175                  | 0                    | 175             | 0.00                 |
| 9    | 164                  | 0                    | 164             | 0.00                 |
| 10   | 162                  | 0                    | 162             | 0.00                 |
| 11   | 175                  | 0                    | 175             | 0.00                 |
| 12   | 163                  | 2                    | 161             | 1.23                 |
| 13   | 168                  | 1                    | 167             | 0.60                 |
| 14   | 177                  | 3                    | 174             | 1.69                 |
| 15   | 155                  | 4                    | 151             | 2.58                 |
| 16   | 160                  | 4                    | 156             | 2.50                 |
| 17   | 169                  | 6                    | 163             | 3.55                 |
| 18   | 164                  | 11                   | 153             | 6.71                 |
| 19   | 167                  | 14                   | 153             | 8.38                 |
| 20   | 157                  | 18                   | 139             | 11.46                |
| 21   | 165                  | 31                   | 134             | 18.79                |
| 22   | 163                  | 46                   | 117             | 28.22                |
| 23   | 170                  | 57                   | 113             | 33.53                |
| 24   | 171                  | 76                   | 95              | 44.44                |
-----------------------------------------------------------------------------------------------
</pre>
