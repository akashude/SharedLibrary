def newDownload(repo)
{
  git "https://github.com/akashude/${repo}.git"
}
def newBuild()
{
  sh 'mvn package'
}

def newDeployment(job,IP,appname)
{
  sh "scp /var/lib/jenkins/workspace/${job}/webapp/target/webapp.war ubuntu@${IP}:/var/lib/tomcat9/webapps/${appname}.war"
}

def newTesting(job,testfile)
{
  sh "java -jar /var/lib/jenkins/workspace/${job}/${testfile}"
}

def newDelivery(submitter,job,IP,appname)
{
  input message: 'Aprove this for Delivery', submitter: ${submitter}
  newDeployment(job,IP,appname)
}
