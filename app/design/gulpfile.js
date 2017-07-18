var gulp = require('gulp');

gulp.task('default', ['build-author', 'build-publish'], function() {});

gulp.task('build-author', function() {
    console.log('Build author!');
});

gulp.task('build-publish', function() {
    console.log('Build publish!');
});