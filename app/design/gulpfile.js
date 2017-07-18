var gulp = require('gulp');
var sass = require('gulp-sass');
var autoprefixer = require('gulp-autoprefixer');
var concat = require('gulp-concat');
var browserify = require('browserify');
var source = require('vinyl-source-stream');

var clientlibsRoot = './src/main/content/jcr_root/etc/designs/example';

function setupClientlib(name) {
    var root = clientlibsRoot + '/' + name;
    var styles = name + '-styles';
    var scripts = name + '-scripts';

    gulp.task(styles, function () {
        return gulp.src(root + '/scss/**/*.scss')
            .pipe(sass().on('error', sass.logError))
            .pipe(autoprefixer())
            .pipe(concat('main.css'))
            .pipe(gulp.dest(root + '/dist'));
    });

    gulp.task(scripts, function () {
        return browserify(root + '/js/main.js')
            .bundle()
            .pipe(source("main.bundle.js"))
            .pipe(gulp.dest(root + '/dist'));
    });

    gulp.task(name, [styles, scripts], function() {});
}

setupClientlib('author');
setupClientlib('publish');

gulp.task('default', ['author', 'publish'], function() {});