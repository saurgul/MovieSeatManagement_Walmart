# Movie Theatre Seating Management Suite

**PROGRAM STATEMENT:**

This program takes a command-line parameter for an input file, reads it line by line, and processes user requests for movie theater seat reservations.

The algorithm follows following rules:

1. Customers who arrive first will be assigned to the last row (rated best view)
2. If the number of available seats in a row exceeds the number of requested seats, each group will be assigned seats in a single row, leaving three seats empty after the group has been accommodated
3. If the number of people in the group exceeds the number of seats in the row, split the group and assign as many seats as are available in that row to a few people, while the rest are assigned to the next available row
4. If the theater is unable to provide nearby (consecutive) seats to a group after scanning all rows, assign seats wherever there is a vacant seat
5. Seats will be assigned in alternate rows, but if the number of reservations exceeds the capacity of alternate seating, the system will begin allocating seats in the rows that have been left empty (FOR COVID Saftey)
6. If the specified number of seats is not available in the theater, inform the consumer that there are insufficient seats

__Assumptions__

1. If the requested number of seats exceeds the available seats, the theater will not be able to book tickets for a group. Customers are notified in this scenario that there are insufficient seats available
2. Once, the alternate rows are filled (initially empty due to COVID Safety), system will stat alloting seats from the second to last row
3. In the input file, the reservation number (R###) will be in sequential sequence (R001, R002, R003,...)

__How are the goals of the problem statement achieved?__

_Customer Satisfaction:_

1. Customers would prefer to sit next to one other because they are booking seats for groups. As a result, the group's first objective will be to find seats in a single row
2. If the reservations are not inundated, there is maximal COIVD safety apart from their group (3 spaces are left empty besides a group and a single row is left empty around them)

_Maximum Theater Utilization:_

1. We begin allocating seats from the first column to ensure that we can accommodate as many groups as feasible while also keeping them satisfied by allocating consecutive seats. This will allow us to seat as many groups as possible in a single row
2. In some situations, if we are unable to allocate a group in a single row, we will assign seats wherever a vacant seat is available in the theater
3. When the number of reservation requests reaches a certain limit, the system starts using the empty rows

__Steps for running the application using command line__
1. Clone the project using the command: ```git clone https://github.com/saurgul/MovieSeatManagement_Walmart.git```
2. Make classes directory to hold all the compiled classes : ```mkdir classes```
2. CD inside the folder MovieTheatreSeaing and comile all java classes : ```javac -d "classes"  $(find ./src/* | grep .java)```
4. Run Main class : ```java -classpath "classes" movie.theatre.seating.Seating input.txt```
