$(document).ready(function () {

    $.ajax({
        url: '/admin/get-all',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (resp) {
            $.each(resp, function (i, item) {
                var row = `<tr>
                                 <td>${item.firstName} ${item.lastName}</td>
                                 <td>${item.age}</td>
                                 <td>${item.username}</td>
                                 <td>${item.phoneNumber}</td>
                        </tr>`;
                $(".table-body").append(row);
            });
        },
        error: function (err) {
            console.log(err);
        }
    });

    $("input").change(function() {
        var name = $(this).val();
        var username = new String(name);
        if (username.length==0){
            $.ajax({
                url: '/admin/get-all',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                success: function (resp) {
                    $(".table-body tr").remove();
                    $.each(resp, function (i, item) {
                        var row = `<tr>
                                 <td>${item.firstName} ${item.lastName}</td>
                                 <td>${item.age}</td>
                                 <td>${item.username}</td>
                                 <td>${item.phoneNumber}</td>
                        </tr>`;
                        $(".table-body").append(row);
                    });
                },
                error: function (err) {
                    console.log(err);
                }
            });
        }
        else {
            $.ajax({
                type: 'GET',
                url: "/admin/get-users/"+name,
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                success: function (resp) {
                    $(".table-body tr").remove();
                    $.each(resp, function (i, item) {
                        var row = `<tr>
                                 <td>${item.firstName} ${item.lastName}</td>
                                 <td>${item.age}</td>
                                 <td>${item.username}</td>
                                 <td>${item.phoneNumber}</td>
                        </tr>`;
                        $(".table-body").append(row);
                    });
                },
                error: function () {
                    alert("error");
                }
            });
        }

    });
});
