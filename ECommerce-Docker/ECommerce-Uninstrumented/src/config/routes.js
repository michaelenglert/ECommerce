module.exports = [
    {
        url: '/checkInventory/id/46212',
        behavior: [
            {
                name: 'responsetime',
                type: 'httpResponseTime',
                vars : [
                    {min : 200, max : 600},
                    {min : 6000, max : 1200},
                    {min : 4000, max : 6000},
                ]
            },
            {
                name: 'statuscode',
                type : 'httpStatusCode',
                vars : [
                    {statusCode : 200},
                    {statusCode : 404},
                    {statusCode : 500}
                ]
            }
        ]
    },
];
