package Controller;

public interface ChatConstants 
{
	int MSGTYPE_LOGIN = 0;
	int MSGTYPE_LOGOUT = 1;
	int MSGTYPE_TEXTCHAT = 2;
	int MSGTYPE_IMAGECHAT = 3;
	int MAX_IMGSIZE = 1024*1024*5;
	
	char SPR = 30;
	char LOGIN_SPR = 31;
	char FRIEND_SPR = 32;
	char CHATS_SPR = 33;
}
