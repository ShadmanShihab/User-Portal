$(document).ready(function () {

    $.ajax({
        url: '/admins/get-all',
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
                url: '/admins/get-all',
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
                url: "/admins/get-users/"+name,
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                success: function (resp) {

                    $.each(resp, function (i, item) {
                        $(".table-body tr").remove();
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


    // $.fn.serializeObject = function () {
    //     var o = {};
    //     var a = this.serializeArray();
    //     $.each(a, function () {
    //         if (o[this.name] !== undefined) {
    //             if (!o[this.name].push) {
    //                 o[this.name] = [o[this.name]];
    //             }
    //             o[this.name].push(this.value || '');
    //         } else {
    //             o[this.name] = this.value || '';
    //         }
    //     });
    //     return o;
    // };
});
