# The core library for E-Cash system #
- model
- repository
- core service

# Build and Deploy step
    - Build & verify:
        mvn -B clean verify package
    - Deploy: e-cash-core library will be deploy as a dependency into private bitbuket repo: https://bitbucket.org/ecashservice/e-cash-core.git under release. Git url: git:releases://https://bitbucket.org/ecashservice/e-cash-core.git
# CI/CD Integration
    - CI/CD: https://app.wercker.com/duyviec/e-cash-core/runs
        1. Wercker to watch all change in https://bitbucket.org/ecashservice/e-cash-core.git
        2. Using docker image to trigger build.
        3. Only master branch will be deployed to bitbucket repository.