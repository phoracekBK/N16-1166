package db_server_webasto;

public class share 
{
	public String copy_and_fill_to_end(String string_in, int char_number, char character_fill, char char_replece)
    {
        int index = 0;
        int input_string_len = string_in.length();
        String copy_and_fill = "";
        if (char_number >= input_string_len)
        {
            while (index <= char_number-1)
            {
                if (index >= (char_number - input_string_len))
                {
                    if(string_in.charAt(index - (char_number - input_string_len)) != char_replece)
                    {
                        copy_and_fill += string_in.charAt(index - (char_number - input_string_len));
                    }
                    else
                        copy_and_fill += character_fill;
                    }
                else
                {
                    copy_and_fill += character_fill;
                }

                index++;
            }
        }
        return copy_and_fill;
    }
    
    public String copy_and_fill_to_start(String string_in, int char_number, char character_fill, char char_replece)
    {
        String copy_and_fill = "";
        int input_string_len = string_in.length();
        int index = 0;
        if(char_number >= input_string_len)
        {
            while(index < char_number)
            {
                if(index<input_string_len)
                {
                    if(string_in.charAt(index) == char_replece)
                    {
                        copy_and_fill += character_fill;
                    }
                    else
                    {
                        copy_and_fill += string_in.charAt(index);
                    }
                }
                else
                {
                    copy_and_fill += character_fill;
                }
                index++;
            }
        }
        
        return copy_and_fill;
    }

    public String get_sub_str(String in, char c)
    {
        String out = "";
        int index = 0;
        while (in.charAt(index) != c)
        {
            out += in.charAt(index);
            index ++;
        }
        
        return out;
    }
}
