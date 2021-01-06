#include <stdio.h>
#include <unistd.h>

int main() {
  char buf[1024];
  int k;
  
  FILE* p = popen("grep \"[02468][02468]\" /etc/passwd | sed \"s/[02468]/=/g\"", "r");
  while(1) {
    k = fread(buf, 1, 1024, p);
    if(k <= 0) {
      break;
    }
    write(1, buf, k);
  }
  pclose(p);
  return 0;
}
