var input = "";
var stack = [];
var output = "";

var operators = ["^", "*", "/", "+", "-"];
var precedence = ["+-", "*/", "^"];
var leftAssociativeOp = ["*", "/", "+", "-"];

$(document).ready(function() {
	$("#intro-container").css("height", $(window).outerHeight());
	$("#intro-container").css("width", $(window).outerWidth());

	$("#start-btn").on("click", function() {
		output = "";
		input = $("#input").val().split(" ").join("");
		stack = [];
		$("#output-display, #stack-display").text("");
		$("#input-display").text(input);
		$("#next-btn, #complete-btn").removeClass("completed");
		$("#intro-container").hide("slide", { direction: "left" }, 500);
	});

	$("#return-btn").on("click", function() {
		$("#intro-container").show("slide", { direction: "left" }, 500);
	});

	$("#next-btn").on("click", function() {
		if (input.length > 0)
		{
			ShauntingYardAlgorithm();
		}
	});

	$("#complete-btn").on("click", function() {
		while (input.length > 0)
		{
			ShauntingYardAlgorithm();
		}
	});

	$("#input").on("input", function() {
		var i = $("#input").val().charAt($("#input").val().length - 1) + "";
		if (isNaN(i) && !operators.includes(i))
		{
			$("#input").val($("#input").val().substring(0, $("#input").val().length - 1));
		}
	});
});

function ShauntingYardAlgorithm()
{
	var token = input.charAt(0);
	input = input.substring(1);

	if (!isNaN(token))
	{
		output += token;
	}
	else if (operators.includes(token))
	{
		do
		{
			var stackTop = stack[stack.length - 1];
			//alert(stackTop);
			if (operators.includes(stackTop))
			{
				var stackTopPr = -1, tokenPr = -1;
				precedence.forEach(function(item, index) {
					if (item.includes(stackTop))
					{
						stackTopPr = index;
						return false;
					}
				});
				precedence.forEach(function(item, index) {
					if (item.includes(token))
					{
						tokenPr = index;
						return false;
					}
				});
				if (stackTopPr >= tokenPr && leftAssociativeOp.includes(token))
				{
					output += stackTop;
					stack.pop();
				}
				else break;
			}
			else break;
		} while (true);

		stack.push(token);
	}
	else if (token == "(")
	{
		stack.push(token);
	}
	else if (token == ")")
	{
		do
		{
			var stackTop = stack[stack.length - 1];
			if (stackTop != "(")
			{
				output += stackTop;
				stack.pop();
			}
			else
			{
				stack.pop();
				break;
			}
		} while (true);
	}
	
	if (input.length == 0)
	{
		do
		{
			output += stack.pop();
		} while (stack.length > 0);
		$("#next-btn, #complete-btn").addClass("completed");
	}

	$("#input-display").text(input);
	$("#output-display").text(output);
	var stackString = "";
	stack.forEach(function(item) {
		stackString += item;
	});
	$("#stack-display").text(stackString);
	
}