(*
Program to calculate factorial from user inputted integer

Cory Iley
February 2019
*)

open Core

(* function to calculate n! *)
let factorial n =
	(* helper func to create list from x-y *)
	let rec range x y = 
		if x > y then []
		else x :: range (x+1) y in

    (* create and display sequence from 1-n *)
    let sequence = range 1 n in
    printf "\nSequence:\n";
    List.iter ~f:(printf "%d ") sequence;

    (* fold list into product using List.fold, and display value *)
    let product = List.fold ~init:1 ~f:( * ) sequence in
    printf "\n\nProduct:\n";
    printf "%d\n\n" product;;

(* prompt user for integer *)
let () =
	printf "Enter an integer to calculate its factorial: ";
	Out_channel.(flush stdout)
	let userInt = Int.of_string In_channel.(input_line_exn stdin);;

	(* function call *)
	factorial userInt;;
