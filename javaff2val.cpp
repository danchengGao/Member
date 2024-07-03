// used to run javaff with wizard

#include "stdio.h"
#include "stdlib.h"
#include "string.h"

char Buffer[1024];
char Domain[64];
char Problem[64];

int main(int ArgC, char *ArgV[]) {
	char Commenter[2] = ";";
	char Extender[2] = "|";
	int  Index;
	
	// Parse Domain Name
	// If the file is empty
	if (scanf("%s", Buffer) == EOF) {		
		printf("%s%s file empty\n",Commenter, Extender);
		return EXIT_FAILURE;
	}		
	
	// Scan until "ID:"
	while (scanf("%s", Buffer) != EOF && strcmp(Buffer,"ID:"));

	// If the next token is not "ID:" or if Buffer is empty
	if (strcmp(Buffer,"ID:") || scanf("%s", Buffer) == EOF) {    
		printf("%s%s wrong format\n",Commenter, Extender);
		return EXIT_FAILURE;	
	}	
    
	// Found domain ID
	// Convert domain ID to lower case
	for(Index = strlen(Buffer) - 1; Index >= 0; Index--)
		if (Buffer[Index] >= 'A' && Buffer[Index] <= 'Z') 
			Buffer[Index] += ('a' - 'A');
	strcpy(Domain, Buffer);	  

	// Parse Problem Name
	// Scan until "ID:"
	while (scanf("%s", Buffer) != EOF && strcmp(Buffer,"ID:"));

	// If the next token is not "ID:" or if Buffer is empty
	if (strcmp(Buffer,"ID:") || scanf("%s", Buffer) == EOF) {
		printf("%s%s wrong format\n",Commenter, Extender);
		return EXIT_FAILURE;	
	}		

	// Found problem ID
	// Convert problem ID to lower case
	for(Index = strlen(Buffer) - 1; Index >= 0; Index--)
		if (Buffer[Index] >= 'A' && Buffer[Index] <= 'Z') 
			Buffer[Index] += ('a' - 'A');
	strcpy(Problem, Buffer);

	// Parse Plan Segment
	// Scan until "plan..." or "No"
	while (scanf("%s", Buffer) != EOF 
		&& strcmp(Buffer,"plan...") && strcmp(Buffer, "No"));

  // If Buffer is "plan...": a plan has been produced
	if (!strcmp(Buffer,"plan...")) {
		// All structured output before the plan segment   
		printf("%s%s (define\n",Commenter, Extender);
		printf("%s%s\t (plan ff-plan)\n", Commenter, Extender);	
		printf("%s%s\t (:domain %s)\n", Commenter, Extender, Domain);
		printf("%s%s\t (:problem %s)\n", Commenter, Extender, Problem);

		// Structured output for the plan segment
		printf("%s%s\t (:steps\n", Commenter, Extender);
		// Scan until "Final"
		while (scanf("%s",Buffer) != EOF && strcmp(Buffer,"Final")) {
        	if (fgets(Buffer, sizeof(Buffer) - 1, stdin) == NULL)
				break;	
			Index = strlen(Buffer) - 1;
			while(Index >= 0 && 
			  (Buffer[Index] == '\r' || Buffer[Index] == '\n' || Buffer[Index] == ' '))
				Index--;
			Buffer[Index+1] = '\0';
			// Convert everything to lower case
			while (Index >= 0) { 
				if (Buffer[Index] >= 'A' && Buffer[Index] <= 'Z') 
					Buffer[Index] += ('a' - 'A');
				Index--;
			}
			Index = 0;
			while(Buffer[Index] == ' ' && Buffer[Index] != '\0') 
				Index++;			
			printf("\t\t%s\n", Buffer + Index);		
		}
		printf("%s%s\t )\n", Commenter, Extender);

		// Skip the next four lines
		for (Index = 0; Index <4; Index ++){            
			fgets(Buffer, sizeof(Buffer) - 1, stdin);
    }

		// Parse Plan Time	
		double Time = 0;	 
 		scanf("%lf", &Time);
  	 	printf("%s%s\t (:time %g)\n", Commenter, Extender, Time);	 	
	 	printf("%s%s )\n", Commenter, Extender);	
		return EXIT_SUCCESS;
	}
	// If Buffer is "plan...": no plan has been produced
	if (!strcmp(Buffer,"No")) {
		printf("%s%s (define\n",Commenter, Extender);
		printf("%s%s\t (plan ff-plan)\n", Commenter, Extender);	
		printf("%s%s\t (:domain %s)\n", Commenter, Extender, Domain);
		printf("%s%s\t (:problem %s)\n", Commenter, Extender, Problem);
		printf("%s%s )\n", Commenter, Extender);
		return EXIT_SUCCESS;
	}
	printf("%s%s planning not finished\n",Commenter, Extender);
	return EXIT_FAILURE;
}
