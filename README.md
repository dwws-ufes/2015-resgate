# 2015-resgate
Assignment for the 2015 edition of the "Web Development and the Semantic Web" course, by Danillo Ricardo Celino and Thiago Martinho Da Costa

Resgate

Para implantar a aplicação, insira o datasource no  standalone.xml do wildfly 8.2.0 final:

<datasource jta="true" jndi-name="java:jboss/datasources/Resgate" pool-name="resgatePool" enabled="true" use-ccm="true">
                    <connection-url>jdbc:mysql://localhost:3306/resgate</connection-url>
                    <driver-class>com.mysql.jdbc.Driver</driver-class>
                    <driver>mysql</driver>
                    <security>
                        <user-name>resgate</user-name>
                        <password>resgate</password>
                    </security>
                    <validation>
                        <validate-on-match>false</validate-on-match>
                        <background-validation>false</background-validation>
                    </validation>
                    <timeout>
                        <set-tx-query-timeout>false</set-tx-query-timeout>
                        <blocking-timeout-millis>0</blocking-timeout-millis>
                        <idle-timeout-minutes>0</idle-timeout-minutes>
                        <query-timeout>0</query-timeout>
                        <use-try-lock>0</use-try-lock>
                        <allocation-retry>0</allocation-retry>
                        <allocation-retry-wait-millis>0</allocation-retry-wait-millis>
                    </timeout>
                    <statement>
                        <share-prepared-statements>false</share-prepared-statements>
                    </statement>
</datasource>

Tenha o wildfly configurado para conexões com MySQL database.
Tenha um schema chamado "resgate".
Tenha um usuário no banco de dados chamado 'resgate' com a senha 'resgate' e que tenha todos os privilégios dentro da base de dados 'resgate'