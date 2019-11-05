function() {
    // karate.configure('connectTimeout', 60000);
    // karate.configure('readTimeout', 60000);

    var env = karate.env || 'dev';
    karate.log("Environment: " + env);

    var publishUrl = karate.properties['test.publishUrl'];
    karate.log("AEM Publish URL: " + publishUrl);

    return {
        env: env,
        publishUrl: publishUrl
    };
}
