# React File Dialog

* 특정 storage 의 디렉토리 탐색, 파일 목록을 출력하는 Storage Common API 와 클라이언트 구동을 위한 서버의 구현이다.
* 로컬 혹은 외부 storage 와 통신하기 위한 서버를 Storage Common API 형태로 프로젝트에 포함한다.
* 해당 어플리케이션은 WAS 로의 embedded 나, standalone 형태로 구동할 수 있다.

# React File Dialog Client Side (Tracer Bullets)

* React Component 형태로 구현.
* 다음의 node module 을 설치해야 함.
 * babel-core
 * babel-loader
 * babel-preset-es2015
 * babel-preset-react
 * webpack
 * style-loader
 * css-loader
 * extract-text-webpack-plugin
 * es6-promise (https://github.com/webpack/css-loader/issues/145 해결될 때까지)
 * copy-webpack-plugin