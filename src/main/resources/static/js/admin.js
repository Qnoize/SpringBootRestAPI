$(document).ready(function() {    getTable();});let tokenB = 'Bearer ' + JSON.parse(sessionStorage.tokenData).token;//get User Tablefunction getTable() {    $.ajax({        type: 'GET',        url: 'http://localhost/rest/admin',        contentType: 'application/json;',        headers: {            'Accept': 'application/json',            'Content-Type': 'application/json',            'Authorization': tokenB        },        dataType: 'JSON',        success: function (listUsers) {            let htmlTable = "";            for (let i = 0; i < listUsers.length; i++) {                htmlTable += (`<tr id="list">                <td class="text-center" id="tableId">${listUsers[i].id}</td>                <td id="tableName">${listUsers[i].username}</td>                <td id="tableEmail">${listUsers[i].email}</td>                <td id="tableRole">${listUsers[i].roles[0].name}</td>                <td><button id="editUserBtn"  class="btn btn-primary" type="button" data-toggle="modal" data-target="#editUsers">Edit</button>                 <button id="deleteUserBtn" class="btn btn-secondary" type="button" data-target="#deleteUsers">Delete</button></td>                </tr><br>`);            }            $("#usersTable #list").remove();            $("#usersTable thead").after(htmlTable);        }    });}//delete User$(document).on('click', '#deleteUserBtn', function () {    let id = $(this).closest("tr").find("#tableId").text();    deleteUser(id);    getTable();});function deleteUser(id) {    $.ajax({        type: 'DELETE',        url: "http://localhost/rest/admin",        contentType: 'application/json;',        headers: {            'Accept': 'application/json',            'Content-Type': 'application/json',            'Authorization': tokenB        },        data: JSON.stringify(id),        success: function () {            getTable();        }    });}//edit User$("#editFormUser").click(function (event) {    event.preventDefault();    editForm();    $("#editUsers").modal('toggle');    getTable();});function editForm() {    let user = {        'id': $("#editId").val(),        'username': $("#editUserName").val(),        'password': $("#editUserPassword").val(),        'email': $("#editUserEmail").val(),    };    $.ajax({        type: 'PUT',        url: "http://localhost/rest/admin",        contentType: 'application/json;',        data: JSON.stringify(user),        headers: {            'Accept': 'application/json',            'Content-Type': 'application/json',            'Authorization': tokenB        },        dataType: 'JSON',        async: false,    });}$(document).on("toggle", "#editUsers", function(){    getTable();});//add User$("#addFormUser").click(function (event) {    event.preventDefault();    addForm();    $(':input', '#addForm').val('');});function addForm() {    let user = {        'username': $("#addUserName").val(),        'password': $("#addUserPassword").val(),        'email': $("#addUserEmail").val()    };    $.ajax({        type: 'POST',        url: "http://localhost/rest/admin",        contentType: 'application/json;',        data: JSON.stringify(user),        headers: {            'Accept': 'application/json',            'Content-Type': 'application/json',            'Authorization': tokenB        },        cache: false,        async: false,        timeout: 600000,        dataType: 'JSON',        success: function (data) {            $('#addingOk').html(data.username);            getTable();        }    });}//modal window$(document).on('click', '#editUserBtn', function () {    $("#editId").val($(this).closest("tr").find("#tableId").text());    $("#editId").prop("disabled", true);    $("#editUserName").val($(this).closest("tr").find("#tableName").text());    $("#editUserPassword").val($(this).closest("tr").find("#tablePassword").text());    $("#editUserEmail").val($(this).closest("tr").find("#tableEmail").text());    $("#editRole").val($(this).closest("tr").find("#tableRole").text());    $("#editRole").prop("disabled", true);});$("#resetTable").click(function () {    getTable();});