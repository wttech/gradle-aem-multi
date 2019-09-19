function() {
    // karate.configure('connectTimeout', 60000);
    // karate.configure('readTimeout', 60000);

    var baseUrl = karate.properties['test.baseUrl'];
    karate.log("Base URL: " + baseUrl);

    var env = karate.env || 'dev';
    karate.log("Environment: " + env);

    return {
        env: env,
        baseUrl: baseUrl
    };
}
