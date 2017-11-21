var input = "";
var stack = [];
var output = "";

var operators = ["^", "*", "/", "+", "-"];
var precedence = ["+-", "*/", "^"];
var leftAssociativeOp = ["*", "/", "+", "-"];

var calculatorStack = [];
var calculatorInput = "";

$(document).ready(function() {
	$("#intro-container").css("height", $(window).outerHeight());
	$("#intro-container").css("width", $(window).outerWidth());

	$("#start-btn").on("click", function() {
		output = "";
		input = $("#input").val().split(" ").join("");
		stack = [];

		if (input.split("(").length == input.split(")").length)
		{
			$("#output-display, #stack-display").text("");
			$("#input-display").text(input);
			$("#result-overall").hide();
			$("#next-btn, #complete-btn").removeClass("completed");
			$("#intro-container").hide("slide", { direction: "left" }, 500);
		}
		else
		{
			alert("Syntax Error");
		}
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

	$("#evaluate-btn").on("click", function() {
		while (input.length > 0)
		{
			ShauntingYardAlgorithm();
		}
		
		calculate();
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

function calculate()
{
	calculatorStack = [];
	calculatorInput = $("#output-display").text();

	for (var i = 0; i < calculatorInput.length; i++)
	{
		var token = calculatorInput.charAt(i);
		if (!isNaN(token))
		{
			calculatorStack.push(token);
		}
		else
		{
			switch (token)
			{
				case "+":
					var n2 = parseFloat(calculatorStack.pop());
					var n1 = parseFloat(calculatorStack.pop());
					//alert("+" + n2 + ", " + n1);
					calculatorStack.push(n1 + n2);
					break;
				case "-":
					var n2 = parseFloat(calculatorStack.pop());
					var n1 = parseFloat(calculatorStack.pop());
					//alert("-" + n2 + ", " + n1);
					calculatorStack.push(n1 - n2);
					break;
				case "*":
					var n2 = parseFloat(calculatorStack.pop());
					var n1 = parseFloat(calculatorStack.pop());
					//alert("-" + n2 + ", " + n1);
					calculatorStack.push(n1 * n2);
					break;
				case "/":
					var n2 = parseFloat(calculatorStack.pop());
					var n1 = parseFloat(calculatorStack.pop());
					calculatorStack.push(n1 / n2);
					//alert("/" + n2 + ", " + n1);
					break;
				case "^":
					var power = parseFloat(calculatorStack.pop());
					var base = parseFloat(calculatorStack.pop());
					//alert("^" + power + ", " + base);
					calculatorStack.push(Math.pow(base, power));
					break;
			}
		}
	}

	$("#result-box").text(calculatorStack.pop());
	$("#result-overall").show("clip", 500);
}