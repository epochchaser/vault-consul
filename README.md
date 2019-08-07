# Vault + Consul 환경구축

## 1. 프로젝트 구성
- vault-docker : Vault 및 Consul 환경에 대한 설정 구성
- vault-client : Vault 서버와 통신하는 Http API

## 2. 백엔드 구성

- Secret Backend : Generic KV-v2
- Storage Backend : Consul

## 3. 도커 환경 구축

1. vault-docker 디렉토리에서 아래의 명령어로 clean build
   ``` bash
   docker-compose down --rmi all
   docker-compose up -d --build
   ```

2. 위 명령어가 정상적으로 실행되면 vault서버 한대, consul서버 클러스터(2대)가 생성된다.
   도커 명령어를 통해 구동중인 서버목록을 확인할 수 있다.
   ``` bash
   docker ps
   ```

3. 아래의 주소로 접속해보면 Web UI 관리페이지를 확인 할 수 있다.
   
   ##### VAULT
   ``` bash
   http://localhost:8200
   ```

   ##### CONSUL
   ``` bash
   http://localhost:8500
   ````
4. Vault init, unseal
   - 방법 1: Web UI를 통해 생성
   - 방법 2: CLI를 통해 생성
        ``` bash
        vault operator init
        vault operator unseal
        ```
5. KV-v2 Secret을 생성한다.
   - 방법 1: Web UI의 Secret탭에서 생성
   - 방법 2: CLI를 통해 생성
        ``` bash
        vault secrets enable -path=secret kv-v2
        ```

## 4. 클라이언트 테스트 환경 구축

1. vault-client 프로젝트의 VaultConfig.java 파일에서 로그인 토큰을 위에서 만든것으로 대체한다.
   ``` java
   new TokenAuthentication("s.kFG2oTvSescQz8B6UcnWm9lN");
   ```
2. vault-client 프로젝트를 실행하여 API 테스트

