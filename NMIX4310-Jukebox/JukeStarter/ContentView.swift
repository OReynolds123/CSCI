//
//  ContentView.swift
//  JukeStarter
//

import SwiftUI
import AVFoundation

var twist: AVAudioPlayer?

struct ContentView: View {
    @State var playerPaused = true
    
    var artist = ["Extreme", "Zac Brown Band", "Jordan Davis", "Dave Matthews Band", "Chris Stapleton", "Blake Shelton"]
    var songTitle = ["More Than Words", "Knee Deep (feat. Jimmy Buffett)", "Buy Dirt (feat. Luke Bryan)", "Satellite", "Tennessee Whiskey", "God's Country"]
    var artistImage = ["moreThanWords", "kneeDeep", "buyDirt", "satellite", "tennesseeWhiskey", "godsCountry"]
    var soundFile = ["moreThanWords.m4a", "kneeDeep.m4a", "buyDirt.m4a", "satellite.m4a", "tennesseeWhiskey.m4a", "godsCountry.m4a"]
    
    @State var numC = 0
        
    var width = 250.0 - 20.0
    var width1 = 250.0 + 40.0
    
    var animate = 0.3
    
    @State var currTime = 0.0
    
    var body: some View {
        
        VStack(alignment: .center) {
            Text("Music")
                .font(.largeTitle)
                .fontWeight(.medium)
                .padding(.top)
                .frame(width: width)
                .onAppear {
                    callFunc()
                }
            
            Spacer()
            
            ZStack {
                songSlide(artist: artist[0], songTitle: songTitle[0], artistImage: artistImage[0], c: currTime, width: width, width1: width1)
                    .offset(x: numC == 1 ? -330 : numC == 0 ? 0 : numC == -1 ? 330 : 0, y: 0)
                    .scaleEffect(numC == 1 ? 0.9 : numC == 0 ? 1 : numC == -1 ? 0.9 : 0)
                    .animation(Animation.linear(duration: animate))
                
                songSlide(artist: artist[1], songTitle: songTitle[1], artistImage: artistImage[1], c: currTime, width: width, width1: width1)
                    .offset(x: numC == 2 ? -330 : numC == 1 ? 0 : numC == 0 ? 330 : 0, y: 0)
                    .scaleEffect(numC == 2 ? 0.9 : numC == 1 ? 1 : numC == 0 ? 0.9 : 0)
                    .animation(Animation.linear(duration: animate))
                
                songSlide(artist: artist[2], songTitle: songTitle[2], artistImage: artistImage[2], c: currTime, width: width, width1: width1)
                    .offset(x: numC == 3 ? -330 : numC == 2 ? 0 : numC == 1 ? 330 : 0, y: 0)
                    .scaleEffect(numC == 3 ? 0.9 : numC == 2 ? 1 : numC == 1 ? 0.9 : 0)
                    .animation(Animation.linear(duration: animate))
                
                songSlide(artist: artist[3], songTitle: songTitle[3], artistImage: artistImage[3], c: currTime, width: width, width1: width1)
                    .offset(x: numC == 4 ? -330 : numC == 3 ? 0 : numC == 2 ? 330 : 0, y: 0)
                    .scaleEffect(numC == 4 ? 0.9 : numC == 3 ? 1 : numC == 2 ? 0.9 : 0)
                    .animation(Animation.linear(duration: animate))
                
                songSlide(artist: artist[4], songTitle: songTitle[4], artistImage: artistImage[4], c: currTime, width: width, width1: width1)
                    .offset(x: numC == 5 ? -330 : numC == 4 ? 0 : numC == 3 ? 330 : 0, y: 0)
                    .scaleEffect(numC == 5 ? 0.9 : numC == 4 ? 1 : numC == 3 ? 0.9 : 0)
                    .animation(Animation.linear(duration: animate))
                
                songSlide(artist: artist[5], songTitle: songTitle[5], artistImage: artistImage[5], c: currTime, width: width, width1: width1)
                    .offset(x: numC == 6 ? -330 : numC == 5 ? 0 : numC == 4 ? 330 : 0, y: 0)
                    .scaleEffect(numC == 6 ? 0.9 : numC == 5 ? 1 : numC == 4 ? 0.9 : 0)
                    .animation(Animation.linear(duration: animate))
            }
            
            HStack {
                Group {
                    Button(action: {
                        if (self.numC - 1 >= 0) {
                            self.numC = self.numC - 1
                        } else {
                            self.numC = artist.count - 1
                        }
                        playsound(thesong: soundFile[numC])
                        if (self.playerPaused) {
                            pauseSong()
                        } else {
                            playSong()
                        }
                    }) {
                        Image(systemName: "backward.fill")
                            .resizable()
                    }
                    Button(action: {
                        playsound(thesong: soundFile[numC])
                        self.playerPaused.toggle()
                        if (self.playerPaused) {
                            pauseSong()
                        } else {
                            playSong()
                        }
                    }) {
                        Image(systemName: playerPaused ? "play.fill" : "pause.fill")
                            .resizable()
                    }
                    Button(action: {
                        if (self.numC + 1 >= artist.count) {
                            self.numC = 0
                        } else {
                            self.numC = self.numC + 1
                        }
                        playsound(thesong: soundFile[numC])
                        if (self.playerPaused) {
                            pauseSong()
                        } else {
                            playSong()
                        }
                    }) {
                        Image(systemName: "forward.fill")
                            .resizable()
                    }
                }
                .aspectRatio(contentMode: .fit)
                .foregroundColor(Color.black)
                .frame(width: 30, height: 30)
                .padding(.horizontal)
                .padding(.vertical, 10.0)
            }
            .padding(20.0)
            .cornerRadius(10)
            .frame(width: width1, height: 75)
            .overlay(
                RoundedRectangle(cornerRadius: 10)
                    .stroke(Color.gray, lineWidth: 1)
            )
            .padding(.all)
            
            Spacer()
        }
        
    }
    func callFunc() {
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
            currTime = twist?.currentTime ?? 0.0
            callFunc()
        }
    }
}

func playsound (thesong: String){
    let thepath = Bundle.main.path(forAuxiliaryExecutable: thesong)!
    let url = URL(fileURLWithPath: thepath)
    do {
        twist = try AVAudioPlayer(contentsOf: url)
    } catch {
        // couldn't load file :(
    }
}


func pauseSong(){
    twist?.pause()
}
func playSong(){
    twist?.play()
}

func getSongDuration() -> (Double) {
    return twist?.duration ?? 29
}

func secondsToHoursMinutesSeconds(_ seconds: Int) -> (Int, Int, Int) {
    return (seconds / 3600, (seconds % 3600) / 60, (seconds % 3600) % 60)
}
func printSecondsToHoursMinutesSeconds(_ seconds: Int) -> (String) {
  let (_, m, s) = secondsToHoursMinutesSeconds(seconds)
  return ("\(m):\(s)")
}
func hoursMinutesSecondsToSeconds(_ hours: Int, _ minutes: Int, _ seconds: Int) -> (Int) {
    return (hours * 3600) + (minutes * 60) + seconds
}

extension AnyTransition {
    static var offsetScaleOpacity: AnyTransition {
        AnyTransition.offset(x: -600, y: 0).combined(with: .scale).combined(with: .opacity)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
