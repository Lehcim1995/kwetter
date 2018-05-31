export interface Owner {
  username: string;
  profilepicture?: string;
  followers ?:  any [];
  following ?: any[];
  groups ?:  any[];
  harts?: any[];
  mentions?: any[];
  bio?: string;
  location?: string;
  website?: string;
  password?: string;
}
