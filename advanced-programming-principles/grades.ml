open Core

type student_info = {
    name : string;
    score : float;
    letter_grade : string;
};;

let calculate_average_score scores =
    let number_of_scores = List.length scores in
    let total_value = List.fold ~init:0 ~f:(+) scores in
    if number_of_scores > 0 then
    	float_of_int total_value /. float_of_int number_of_scores
    else 0.
;;

let determine_letter_grade average =
    if average >= 90.0 then "A"
    else if average < 90.0 && average >= 80.0 then "B"
    else if average < 80.0 && average >= 70.0 then "C"
    else if average < 70.0 && average >= 60.0 then "D"
    else "F"
;;

let rec read_scores score =
	(* ensure string can be cast as int *)
	let is_int s =
		try ignore (int_of_string s); true
		with _ -> false in
  	
    let line = In_channel.(input_line_exn stdin) in
    	match line with
    	| "" -> score
    	| x when is_int x && Int.of_string x >= 0 && Int.of_string x <= 100 -> Int.of_string x :: read_scores []
    	| _ ->
    		printf "Invalid input - integers from 0-100 only\n";
    		Out_channel.(flush stdout);
    		read_scores []
;;

let () =
	printf "Enter the student's name: ";
	Out_channel.(flush stdout)
	let student_name = In_channel.(input_line_exn stdin);;
	
	printf "Enter successive scores between 0-100.  Leave blank to perform calculation.\n";
	Out_channel.(flush stdout)
	let scores_list = read_scores [];;
	
	let average_score = (calculate_average_score scores_list) in
	let grade = determine_letter_grade average_score in
	
	let student = {
		name = student_name;
		score = average_score;
		letter_grade = grade;
	} in
	
	printf "Name: %s\nAverage: %f\nGrade: %s\n\n" student.name student.score student.letter_grade;;
