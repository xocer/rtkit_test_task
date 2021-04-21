<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.0/font/bootstrap-icons.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"
            type="text/javascript"></script>

    <script>
        $(document).ready(function () {
            $.ajax({
                url: "http://localhost:8080/rtkit/colors",
                type: "GET",
                data: {action: "all"},
                dataType: 'json'
            }).done(function (data) {
                let result = "";
                $.each(data, function (index) {
                    result += "<tr>" +
                        "<td>" + data[index]['colorNumber'] + "</td>" +
                        "<td onclick='getColor(" + data[index]['id'] + ")'>" + data[index]['colorName'] + "</td>" +
                        "</tr>"
                });
                $('#colors').html(result)
            });
        });

        function getColor(id) {
            $.ajax({
                url: "http://localhost:8080/rtkit/colors",
                type: "GET",
                data: {action: "one", id: id},
                dataType: 'json'
            }).done(function (data) {
                let result = data['colorNumber'] + ", " + data['colorName'];

                $('#message').css("color", data['colorName'].replace(/\s/g, '')).html(result);
            });
        }
    </script>

    <title>Colors</title>
</head>
<body>
<div class="container">
    <dev class="card-body">
        <h2 id="message"></h2><br>
    </dev>
    <div class="card-body">
        <table class="table table-hover table-bordered">
            <thead>
            <th>Color number</th>
            <th>Color name</th>
            </thead>
            <tbody id="colors">
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
