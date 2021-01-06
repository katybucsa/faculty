#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>

pid_t* pids;
int n;

void stop(int sig) {
  int i;
  for(i=0; i<n; i++) {
    kill(pids[i], SIGKILL);
  }
}

int main(int argc, char** argv) {
  int i, k;
  char** a;

  a = (char**)malloc(argc*sizeof(char**));
  for(i=1; i<argc; i++) {
    if(strcmp(argv[i], ";") == 0) {
      a[i-1] = NULL;
      n++;
    }
    else {
      a[i-1] = argv[i];
    }
  }

  a[argc-1] = NULL;
  n++;
  pids = (pid_t*)malloc(n*sizeof(pid_t));

  k = 0;
  for(i=0; i<n; i++) {
    pids[i] = fork();
    if(pids[i] == 0) {
      execvp((a+k)[0], a+k);
      exit(0);
    }
    while(a[k] != NULL) {
      k++;
    }
    k++;
  }
  free(a);
  
  signal(SIGALRM, stop);
  alarm(5);

  for(i=0; i<n; i++) {
    wait(0);
  }

  free(pids);
  return 0;
}
