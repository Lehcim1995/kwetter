export interface Kweet {
  id: number;
  mentions?: any[],
  trends?: any[];
  harts?: any[];
  message: string;
  ownerName: string;
  postDate: string;
}
