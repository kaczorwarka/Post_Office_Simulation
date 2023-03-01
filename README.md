# POST OFFICE SIMULATION

## DESCRIPTION :pencil2:
This project is simply textual simulation of post office. It was a subject passing task at the Military University of Technology.

## MAIN ASSUMPTIONS :scroll:
* Customers arrive at the facility at random intervals (exponential distribution with **lambda** parameter).
* Customers entering the facility always stand in line (FIFO). The facility has a limited capacity **L** and if there is no space they leave the facility (loss).
* Customers are served by a certain number **M** of service windows characterized by the same work algorithm and service time distribution 
(exponential distribution with parameter **mi**).
* Each service window works continuously inviting the first customer from the queue to the window and terminates service after a random time.
* The customer at the end of service with probability **p** again stands at the end of the queue (if there is no room leaves the system - loss) 
or with (1 - **p**) leaves the facility.
* Each customer is impatient and, standing at the end of the queue, has a limited random time to wait for service to begin (exponential distribution with parameter **ro**). 
If his service does not start by that time, he leaves the facility (loss). Once service begins, the interested party becomes patient.
* The computer system used at customer service desks is subject to alternating failures and repairs. 
The times of fitness and inability are random (exponential distribution with parameters **alpha**, **beta**). 
As a result of a failure, the windows stop working immediately, and the served customers return to the beginning of the queue. 
The effect of the repair is the immediate resumption of work of the windows as long as there are interested parties in the queue.

## IMPORTANT TO NOTE :triangular_flag_on_post:
The project use seperate library **dissimlab** given by professor for example to generate diagram or random values in simulation.
