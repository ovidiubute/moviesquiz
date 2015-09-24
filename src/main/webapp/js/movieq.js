angular
    .module('movieQuiz', ['ui.sortable'])
    .controller('QuizController', function ($scope, $http) {
        $scope.items = [];
        $scope.ordinal = 1;
        $scope.quizId = undefined;

        $scope.getQuestion = function () {
            $http.get('/api/quiz/questions?ordinal=' + $scope.ordinal).success(function (data) {
                $scope.items = data.movies;
            });
        }

        $scope.submitAnswer = function () {
            $http.post('/api/quiz/' + $scope.quizId + '/answers?ordinal=' + $scope.ordinal, {movieIds: _.pluck($scope.items, 'id')}).success(function (data) {
                if (!data.finished) {
                    $scope.ordinal++;
                    if (data.lastAnswerOK) {
                        console.log('OK');
                    } else {
                        console.log('NOK');
                    }
                    $scope.getQuestion();
                } else {
                    console.log('FINISHED');
                }
            });
        }

        $scope.startQuiz = function () {
            $http.post('/api/quiz/', {"owner":{"id":1,"username":"ovidiu","email":"ovidiu@xyz.com"}}).success(function (data) {
                $scope.quizId = data.id;
            });
        }

        $scope.getQuestion();
    });
